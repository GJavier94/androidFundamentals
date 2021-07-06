package eu.example.images

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class Fragment_content_country : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_content_country, container, false)
        var pos = arguments?.getInt("position", 0)

        val imageView = view.findViewById<ImageView>(R.id.fcc_imageView)

        when(pos){
            0 -> imageView?.setImageResource(R.drawable.amsterdan)
            1 -> imageView?.setImageResource(R.drawable.athenas)
            2 -> imageView?.setImageResource(R.drawable.berlin)
            3 -> imageView?.setImageResource(R.drawable.roma)
            4 -> imageView?.setImageResource(R.drawable.tokio)
        }
        return view
    }

}