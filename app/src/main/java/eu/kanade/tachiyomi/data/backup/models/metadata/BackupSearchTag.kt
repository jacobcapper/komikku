package eu.kanade.tachiyomi.data.backup.models.metadata

import exh.metadata.sql.models.SearchTag
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class BackupSearchTag(
    @ProtoNumber(1) var namespace: String? = null,
    @ProtoNumber(2) var name: String,
    // Needs a default: proto3 omits the field on the wire when it's 0, and without a default
    // here kotlinx.serialization throws MissingFieldException on decode.
    @ProtoNumber(3) var type: Int = 0,
) {
    fun getSearchTag(mangaId: Long): SearchTag {
        return SearchTag(
            id = null,
            mangaId = mangaId,
            namespace = namespace,
            name = name,
            type = type,
        )
    }

    companion object {
        fun copyFrom(searchTag: SearchTag): BackupSearchTag {
            return BackupSearchTag(
                namespace = searchTag.namespace,
                name = searchTag.name,
                type = searchTag.type,
            )
        }
    }
}
