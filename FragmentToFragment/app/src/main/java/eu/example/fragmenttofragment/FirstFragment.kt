package eu.example.fragmenttofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager

class FirstFragment : Fragment() {


    private var editTextPersonName: EditText? = null
    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_first, container, false)
        editTextPersonName = view.findViewById<EditText>(R.id.editTextTextPersonName)
        button = view.findViewById<Button>(R.id.button)

        button?.setOnClickListener{
            var name = editTextPersonName?.text.toString()
            var bundle = Bundle()
            bundle.putString("name", name)

            //creating the second fragment
            var secondFragment = SecondFragment()
            secondFragment.arguments = bundle
            val fragmenTransaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
            fragmenTransaction.replace(R.id.FrameLayout,secondFragment )
            fragmenTransaction.addToBackStack(null)
            fragmenTransaction.commit()

        }
        return view
    }

}