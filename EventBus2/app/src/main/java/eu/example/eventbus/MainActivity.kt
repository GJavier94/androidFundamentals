package eu.example.eventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import org.greenrobot.eventbus.EventBus

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById<EditText>(R.id.editText)
        button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            EventBus.getDefault().post(Message(editText.text.toString()))
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentA = FragmentA()
        fragmentTransaction.add(R.id.frameLayout, fragmentA,"FragmentA")
        fragmentTransaction.commit()

    }
}