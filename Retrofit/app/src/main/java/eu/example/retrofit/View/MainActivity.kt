package eu.example.retrofit.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.example.retrofit.ModelClass
import eu.example.retrofit.R
import eu.example.retrofit.RecyclerAdapter
import eu.example.retrofit.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView:RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var data:List<ModelClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUIComponents()

        recyclerView.layoutManager = LinearLayoutManager(this.applicationContext)



        //declaring the api use
        val retrofit = Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitApi = retrofit.create(RetrofitApi::class.java)


        val call: Call<List<ModelClass>> = retrofitApi.getModelClass()
        call.enqueue( object : Callback<List<ModelClass>>{
            override fun onResponse(
                call: Call<List<ModelClass>>,
                response: Response<List<ModelClass>>
            ) {
                // it could be a problem with the response (error 404 or 500)
                data = response.body()!!
                recyclerAdapter = RecyclerAdapter(data)
                recyclerView.adapter = recyclerAdapter

            }

            override fun onFailure(call: Call<List<ModelClass>>, t: Throwable) {
                Toast.makeText(this@MainActivity.applicationContext, "There was an error " + t.localizedMessage , Toast.LENGTH_LONG  )
                /*
                It could be a problem creating the request
                sending the request and having a network problem
                receiving the response and not able to processing it
                 */
            }

        })
    }

    private fun getUIComponents() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
    }

    fun startVideos(view: View) {
        startActivity(VideosActivity.launch(this))
    }
}