package eu.example.fragmenttoactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {


    private var buttonSendData: Button? =null
    private var editextName: EditText?=null
    private var editextEmail: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_first_fragmeent, container, false)

        Log.i("${this.javaClass.name} onCreateView", "calling onCreateView()... ")
        editextEmail = view?.findViewById<EditText>(R.id.editTextEmail)
        editextName = view?.findViewById<EditText>(R.id.editTextName)
        buttonSendData = view?.findViewById<Button>(R.id.buttonSendData)

        if(buttonSendData != null ){
            buttonSendData?.setOnClickListener{
                val email = editextEmail?.text.toString()
                val name  = editextName?.text.toString()
                (activity as MainActivity).sendData(email, name)
            }
        }else{
            Log.e("FirstFragment", "button null")
        }


        // Inflate the layout for this fragment
        return view
    }

}



