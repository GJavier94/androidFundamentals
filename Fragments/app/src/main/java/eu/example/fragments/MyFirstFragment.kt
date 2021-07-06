package eu.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyFirstFragment(counter: Int) : Fragment() {
    private var counter = counter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_my_first, container, false)

        var textViewResult = view!!.findViewById<TextView>(R.id.textViewResult)
        textViewResult.text = counter.toString()

        if(arguments != null ) {
            val weight= requireArguments().get("weight") as Double
            val height =  requireArguments().get("height") as Double
            val result:Double = calculateBMI(weight, height)
            textViewResult.text = result.toString()
        }
        return view
    }



    private fun calculateBMI(weight: Double, height: Double): Double {
        return (weight * 1000) / (height * height)
    }


}