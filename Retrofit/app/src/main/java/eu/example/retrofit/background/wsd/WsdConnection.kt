package eu.example.retrofit.background.wsd

import eu.example.retrofit.RetrofitApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WsdConnection {
    companion object{
        //declaring the api use
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://cmsapp.totalplay.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val VideosEndPoint  = retrofit.create(WSD_cms::class.java)

    }
}
