package eu.example.sendemail

import android.app.Notification
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.net.URI

class MainActivity : AppCompatActivity() {
    private var sendButton: Button? = null
    private var editTextBody: EditText? = null
    private var editTextSubject: EditText? = null
    private var editTextTextEmailAddress: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTextEmailAddress = findViewById<EditText>(R.id.editTextTextEmailAddress)
        editTextSubject = findViewById<EditText>(R.id.editTextSubject)
        editTextBody = findViewById<EditText>(R.id.editTextBody)
        sendButton = findViewById<Button>(R.id.button)

        sendButton?.setOnClickListener(View.OnClickListener {
            sendMessage(editTextTextEmailAddress?.text.toString(), editTextSubject?.text.toString(), editTextBody?.text.toString())
        })
    }

    private fun sendMessage(emailAddress: String, subject: String, body: String) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body,)
        println(intent?.toUri(Intent.URI_ALLOW_UNSAFE))

        startActivity(Intent.createChooser(intent,"Send Email"))

    }
}







