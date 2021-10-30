package eu.example.retrofit.background.wsd.Response

import com.google.gson.annotations.SerializedName

data class  VideosResponse(

    val id: Long,
    @SerializedName("Titulo")
    val titulo: String?,
    val descripcion: String?,
    val ordenAparicion: Long,
    @SerializedName("Negocio")
    val negocio: Boolean,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    val video: List<MediaMetadata>,
    val miniatura: List<MediaMetadata>
)




data class MediaMetadata (
    val id: Long,
    val name: String,
    val alternativeText: String,
    val caption: String,
    val width: Long? = null,
    val height: Long? = null,
    val formats: Formats? = null,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val url: String,

    @SerializedName("previewUrl")
    val previewURL: String? = null,

    val provider: String,

    @SerializedName("provider_metadata")
    val providerMetadata: String? = null,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String
)


data class Formats (
    val thumbnail: Thumbnail
)

data class Thumbnail (
    val hash: String,
    val ext: String,
    val mime: String,
    val width: Long,
    val height: Long,
    val size: Double,
    val path: Any? = null,
    val url: String
)


