package eu.example.informationbook.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import eu.example.informationbook.R
import java.lang.Exception


class FragmentFrance : Fragment() {


    private var progressBar: ProgressBar? = null
    private var textViewFrance: TextView? = null
    private var imageView: ImageView?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_france, container, false)
        imageView = view.findViewById<ImageView>(R.id.imageViewFrance)
        textViewFrance = view.findViewById<TextView>(R.id.textViewFrance)
        progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Flag_of_France.svg/1200px-Flag_of_France.svg.png")
            .into(
                imageView,
                  object : Callback {
                      override fun onSuccess() {
                          progressBar?.visibility = View.INVISIBLE
                      }
                      override fun onError(e: Exception?) {
                          Toast.makeText(context, e?.localizedMessage, Toast.LENGTH_SHORT)
                          progressBar?.visibility = View.VISIBLE
                      }
                  }
            )
        return view
    }
    companion object{
        fun newInstance():FragmentFrance{
            return FragmentFrance()
        }
    }
}