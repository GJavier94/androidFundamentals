package eu.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_INTERNAL_STORAGE_SETTINGS

import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var Tag = "Activity One"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(Tag, "Oncreate called")
        val btn = findViewById(R.id.btn) as Button
        btn.setOnClickListener(View.OnClickListener {
           it -> var intent  = Intent()
            intent.setAction(Settings.ACTION_INTERNAL_STORAGE_SETTINGS)
            startActivity(intent)
        })
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(Tag, "onRestart called")
    }
    override fun onStart() {
        super.onStart()
        Log.i(Tag, "OnStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.i(Tag, "Onresume called")

    }

    override fun onPause() {
        super.onPause()
        Log.i(Tag, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(Tag, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(Tag, "onDestroy() called")
    }
}



