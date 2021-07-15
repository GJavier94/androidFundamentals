package eu.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import eu.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val info = Info("javier", "Armenta", "javarmgar@gmail.com")
        binding.data = info


    }
}