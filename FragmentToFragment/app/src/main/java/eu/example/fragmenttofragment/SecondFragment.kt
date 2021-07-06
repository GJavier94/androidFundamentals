package eu.example.fragmenttofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class SecondFragment : Fragment() {


    private var textViewName: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_second, container, false)

        textViewName = view.findViewById<TextView>(R.id.TextViewName)

        val bundle = arguments
        var name:String = bundle?.get("name").toString() ?: "unknown"

        textViewName?.text = name

        return view
    }

}