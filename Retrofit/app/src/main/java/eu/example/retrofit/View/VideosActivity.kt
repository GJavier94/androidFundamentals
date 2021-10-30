package eu.example.retrofit.View

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import eu.example.retrofit.R
import eu.example.retrofit.ViewModel.VideosViewModel

class VideosActivity : AppCompatActivity() {

    val videosViewModel by viewModels<VideosViewModel>()// initialization using ktx extension

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)
        Log.d(LOG_TAG, "calling the totalPlay VIDEOS API...")
        videosViewModel.getAllVideos().observe(this, Observer {
            listVideos ->
            if(listVideos != null ){
                listVideos.forEach {
                    videosResponse ->
                    Log.d(LOG_TAG, videosResponse.toString())
                }
            }else{
                Log.d(LOG_TAG, "There was an error , the List of Videos is null ")
            }
        })

    }


    companion object{
        const val LOG_TAG = "LVideosActivity"
        fun launch(context: Context): Intent {
            return Intent(context, VideosActivity::class.java)
        }
    }
}