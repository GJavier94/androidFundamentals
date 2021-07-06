package eu.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private var heightET: EditText? = null
    private var weightET: EditText? = null
    private var btnCalculate: Button? = null
    private var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting the fragment manager an starting transaction by invoking fragment method
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        //creating the actual fragment
        val myFirstFragment = MyFirstFragment(++count)
        //getting the components from the main activity


        heightET = findViewById<EditText>(R.id.editTextWeight)
        weightET = findViewById<EditText>(R.id.editTextWeight)

        btnCalculate = findViewById(R.id.buttonCalculate) as Button

        btnCalculate!!.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble("weight", (weightET!!).text.toString().toDouble()  )
            bundle.putDouble("height", (heightET!!).text.toString().toDouble() )

            myFirstFragment.arguments = bundle

            fragmentTransaction.add(R.id.frame,myFirstFragment)
            fragmentTransaction.commit()


        }



    }



}