package eu.example.dialogfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment


class FragmentoDialogo : DialogFragment() {


    private var btnCancel: Button? = null
    private var btnAccept: Button? = null
    private var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dialog, container, false)
        editText = view.findViewById<EditText>(R.id.editText)

        btnAccept = view.findViewById<Button>(R.id.df_accept)
        btnCancel = view.findViewById<Button>(R.id.df_cancel)

        btnAccept?.setOnClickListener{
            (activity as MainActivity).textView_resultDialog?.text = editText?.text.toString()
            this.dismiss()
        }
        btnCancel?.setOnClickListener{
            this.dismiss()
        }

        return view
    }

}







