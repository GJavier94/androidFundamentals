package eu.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class SignInWithPhone : AppCompatActivity() {
    private lateinit var editTextCodeSMS: EditText
    private lateinit var buttonSendSms: Button
    private lateinit var editTextPhone: EditText
    private lateinit var buttonSignInWithPhone: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var codeSent:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_with_phone)



        getComponents()
        firebaseAuth = Firebase.auth

        buttonSendSms.setOnClickListener{
            val phoneNumber = editTextPhone.text.toString()
            sendSMS(phoneNumber)
        }

        buttonSignInWithPhone.setOnClickListener{
            val code:String =  editTextCodeSMS.text.toString()
            signInWithPhone(code)
        }
    }

    private fun signInWithPhone(code: String) {
        val phoneAuthCredential = PhoneAuthProvider.getCredential(this.codeSent, code)
        signInWithPhoneAuthCredential(phoneAuthCredential)
    }

    private fun signInWithPhoneAuthCredential(phoneAuthCredential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this){
            task->
            if(task.isSuccessful){
                Log.d(TAG, "signInWithPhoneAuthCredential:Success")
                Toast.makeText(this.applicationContext,"signInWithPhoneAuthCredential:Success", Toast.LENGTH_SHORT )

                val intent = Intent(this.applicationContext, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Log.d(TAG, "signInWithPhoneAuthCredential:Failure")
                Toast.makeText(this.applicationContext,"signInWithCredential:Failure", Toast.LENGTH_SHORT )

            }
        }
    }

    private fun sendSMS(phoneNumber: String) {

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(
                object:PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        Log.d(TAG, "onVerificationCompleted:Success")
                        Toast.makeText(this@SignInWithPhone,"onVerificationCompleted:Success", Toast.LENGTH_SHORT )
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        Log.d(TAG, "onVerificationCompleted:Failure" + p0.stackTrace)
                        Toast.makeText(this@SignInWithPhone,"onVerificationCompleted:Failure", Toast.LENGTH_SHORT )

                    }

                    override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                            super.onCodeSent(p0, p1)
                        Log.d(TAG, "onCodeSent:Success " + p0)
                        Toast.makeText(this@SignInWithPhone,"onCodeSent:${p0}", Toast.LENGTH_SHORT )
                        this@SignInWithPhone.codeSent = p0
                    }

                }
            )// OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun getComponents() {
        editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        buttonSendSms = findViewById<Button>(R.id.buttonSendSms)
        editTextCodeSMS = findViewById<EditText>(R.id.editTextCodeSMS)
        buttonSignInWithPhone = findViewById<Button>(R.id.ButtonSignInPhone)
    }

    companion object{
        const val TAG = "SignInWithPhone"
    }

}