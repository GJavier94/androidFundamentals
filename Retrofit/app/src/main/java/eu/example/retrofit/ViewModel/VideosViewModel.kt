package eu.example.retrofit.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.example.retrofit.background.wsd.Repository.VideosRepository
import eu.example.retrofit.background.wsd.Response.VideosResponse

class VideosViewModel(
    private val videosRepository: VideosRepository =  VideosRepository()
) : ViewModel() {


    fun getAllVideos(): MutableLiveData<List<VideosResponse>?> {
        return videosRepository.getAllVideos()
    }

}
