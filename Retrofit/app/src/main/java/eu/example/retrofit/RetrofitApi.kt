

import eu.example.retrofit.ModelClass
import java.util.List
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @GET("posts")
    fun getModelClass():Call<List<ModelClass>>
}