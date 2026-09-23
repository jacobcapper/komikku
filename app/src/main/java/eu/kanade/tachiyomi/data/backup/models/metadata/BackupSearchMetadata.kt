package eu.kanade.tachiyomi.data.backup.models.metadata

import exh.metadata.sql.models.SearchMetadata
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class BackupSearchMetadata(
    @ProtoNumber(1) var uploader: String? = null,
    // Needs a default: proto3 omits the field on the wire when it's the zero value (""/0),
    // and without a default here kotlinx.serialization throws MissingFieldException on decode.
    @ProtoNumber(2) var extra: String = "",
    @ProtoNumber(3) var indexedExtra: String? = null,
    @ProtoNumber(4) var extraVersion: Int = 0,
) {
    fun getSearchMetadata(mangaId: Long): SearchMetadata {
        return SearchMetadata(
            mangaId = mangaId,
            uploader = uploader,
            extra = extra,
            indexedExtra = indexedExtra,
            extraVersion = extraVersion,
        )
    }

    companion object {
        fun copyFrom(searchMetadata: SearchMetadata): BackupSearchMetadata {
            return BackupSearchMetadata(
                uploader = searchMetadata.uploader,
                extra = searchMetadata.extra,
                indexedExtra = searchMetadata.indexedExtra,
                extraVersion = searchMetadata.extraVersion,
            )
        }
    }
}
