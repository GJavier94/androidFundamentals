package eu.example.images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ListFragment = MainFragment()
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, ListFragment)
        fragmentTransaction.commit()

    }
}