package eu.example.retrofit.background.wsd.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import eu.example.retrofit.background.wsd.Response.VideosResponse
import eu.example.retrofit.background.wsd.WSD_cms
import eu.example.retrofit.background.wsd.WsdConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideosRepository(
    private val wsdConnectionEndPoint: WSD_cms = WsdConnection.VideosEndPoint
) {


    fun getAllVideos():MutableLiveData<List<VideosResponse>?>{
        val videosResponseListLD = MutableLiveData<List<VideosResponse>?>()

        val call: Call<List<VideosResponse>> = wsdConnectionEndPoint.getAllVideos()
        call.enqueue(object: Callback<List<VideosResponse>>{
            override fun onResponse(
                call: Call<List<VideosResponse>>,
                response: Response<List<VideosResponse>>
            ) {
                when(response.isSuccessful){
                    true ->{
                        videosResponseListLD.value = response.body()
                    }
                    false -> {
                        videosResponseListLD.value = null
                    }
                }
            }
            override fun onFailure(call: Call<List<VideosResponse>>, t: Throwable) {
                Log.d(LOG_TAG, "getAllVideos: call onFailure.. ${t}")
                videosResponseListLD.value = null
            }
        })
        return videosResponseListLD
    }
    companion object{
        val LOG_TAG = "LVideosRepository"
    }
}


