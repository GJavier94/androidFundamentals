package eu.example.retrofit.background.wsd

import eu.example.retrofit.ModelClass
import eu.example.retrofit.background.wsd.Response.VideosResponse
import retrofit2.Call
import retrofit2.http.GET

interface WSD_cms {

    @GET("/videos")
    fun getAllVideos(): Call<List<VideosResponse>>

}
