package eu.example.retrofit

import com.google.gson.annotations.SerializedName

class ModelClass(userId:String,id:String, title:String, subtitle:String ) {

    val userId = userId

    val id = id
    
    val title = title

    @SerializedName("body")
    val subtitle = subtitle


}