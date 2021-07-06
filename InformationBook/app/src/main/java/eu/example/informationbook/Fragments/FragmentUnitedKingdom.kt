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
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import eu.example.informationbook.R
import java.lang.Exception


class FragmentUnitedKingdom : Fragment() {


    private var progressBar: ProgressBar? = null
    private var textViewUnitedKingdom: TextView? = null
    private var imageView: ImageView?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_united_kingdom, container, false)
        imageView = view.findViewById<ImageView>(R.id.imageViewUnitedKingdom)
        textViewUnitedKingdom = view.findViewById<TextView>(R.id.textViewUnitedKindom)
        progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1200px-Flag_of_the_United_Kingdom.svg.png")
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
        fun newInstance():FragmentUnitedKingdom{
            return FragmentUnitedKingdom()
        }
    }
}