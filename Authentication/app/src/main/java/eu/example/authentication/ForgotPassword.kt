package eu.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPassword : AppCompatActivity() {

    private lateinit var textViewEmail:EditText
    private lateinit var buttonResetPassword: Button
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var textViewResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        getComponents()
        firebaseAuth = Firebase.auth

        buttonResetPassword.setOnClickListener{
            val email = textViewEmail.text.toString()
            sendEmail(email)

        }

    }

    private fun sendEmail(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(this){
                task ->
            if(task.isSuccessful){
                Log.d(TAG, "sendPasswordResetEmail:Success... Email sent.")
                textViewResponse.text = "sendPasswordResetEmail:Success... Email sent."
            }
            else{
                Log.d(TAG, "sendPasswordResetEmail:Failure... Email not sent: Internal error")
                textViewResponse.text = "sendPasswordResetEmail:Failure... Email not sent: Internal error"
            }
            val intent = Intent(this.applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getComponents() {
        buttonResetPassword = findViewById(R.id.buttonResetPassword)
        textViewResponse = findViewById(R.id.textViewResponse)
        textViewEmail = findViewById<EditText>(R.id.editTextEmailAddressRecovery)
    }

    companion object{
        const val TAG = "ForgotPassword"
    }
    
}