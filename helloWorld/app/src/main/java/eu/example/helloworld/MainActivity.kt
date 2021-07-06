package eu.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ClickMeButton:Button = findViewById(R.id.ClickMe) as Button
        var textCount:TextView = findViewById(R.id.ClickCount) as TextView

        var count = 0
        ClickMeButton.setOnClickListener(){
           if(textCount.text.equals("")) textCount.setText(count.toString())

            try {
                count++
                textCount.setText((count).toString())
                Toast.makeText(this@MainActivity, "you clicked me ${count} times", Toast.LENGTH_SHORT).show()
            }catch (e:NumberFormatException){
                println("There's an error")
            }
        }


    }

}