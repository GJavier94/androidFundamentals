package eu.example.simpleui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val message:String = this.intent.getStringExtra(EXTRA_MESSAGE) ?: ""
        Log.d("DisplayMessageActivity", message)
        findViewById<TextView>(R.id.textView1).apply {
            this.text = message
        }
    }

}