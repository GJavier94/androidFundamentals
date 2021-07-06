package eu.example.fragmenttofragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creating the FirstFragment
        var firstFragment = FirstFragment()
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.FrameLayout, firstFragment)
        fragmentTransaction.commit()


    }

}