package eu.example.images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SecondActivity : AppCompatActivity() {


    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var pos = intent.getIntExtra("position", 0)
        imageView = findViewById<ImageView>(R.id.actSecond_imageView)

        when(pos){
            0 -> imageView?.setImageResource(R.drawable.amsterdan)
            1 -> imageView?.setImageResource(R.drawable.athenas)
            2 -> imageView?.setImageResource(R.drawable.berlin)
            3 -> imageView?.setImageResource(R.drawable.roma)
            4 -> imageView?.setImageResource(R.drawable.tokio)
        }
    }
}