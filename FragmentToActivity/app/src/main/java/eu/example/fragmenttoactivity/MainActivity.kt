package eu.example.fragmenttoactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {


    private var textViewEmail: TextView? = null
    private var texViewName: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texViewName = findViewById<TextView>(R.id.textViewName)
        textViewEmail = findViewById<TextView>(R.id.textViewEmail)


        var fragment = FirstFragment()

        //getting an instance of the fragment manager
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.linear_main, fragment)
        fragmentTransaction.commit()

    }
    fun sendData(email:String, name:String){

        textViewEmail?.text = email ?: "Campo vacio"
        texViewName?.text = name ?:"Campo vacio"

    }
}