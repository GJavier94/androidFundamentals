package eu.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private var buttonSignUp: Button? = null
    private var buttonSignIn: Button?  = null
    private var editTextPassword: EditText? = null
    private var editTextEmail: EditText? = null
    private var buttonSignInWithPhone:Button? = null
    //this variable gets initialized on the method onCreate()
    private lateinit var fireBaseAuth: FirebaseAuth
    private lateinit var buttonForgotPassword:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate()...")
        buttonSignInWithPhone = findViewById<Button>(R.id.buttonSignWithPhone)

        getComponents()
        fireBaseAuth = Firebase.auth

        buttonSignIn?.setOnClickListener{
            val email = editTextEmail?.text.toString()
            val password = editTextPassword?.text.toString()
            signIn(email,password)

        }

        buttonSignUp?.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)

        }
        buttonForgotPassword.setOnClickListener{
            val intent  = Intent(this.applicationContext, ForgotPassword::class.java)
            startActivity(intent)
        }
        buttonSignInWithPhone?.setOnClickListener{
            val intent  = Intent(this.applicationContext, SignInWithPhone::class.java)
            startActivity(intent)

        }
    }

    private fun getComponents() {
        editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        buttonSignIn = findViewById<Button>(R.id.ButtonSignIn)
        buttonSignUp = findViewById<Button>(R.id.ButtonSignUp)
        buttonForgotPassword  = findViewById<Button>(R.id.buttonForgotPassword)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()...")

        if(fireBaseAuth.currentUser != null){
            Log.d(TAG, "onStart()... welcome ${fireBaseAuth.currentUser!!.email}")
            reload()// this is done when there's a user instance -> it means the user is autenticated

        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()...")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()...")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()...")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()...")
    }
    private fun reload() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun signIn(email: String, password: String) {
        fireBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                task ->
            if(task.isSuccessful){
                Log.d(TAG, "signIn:Success"  )
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Log.d(TAG, "signIn:Failure"  )
                Toast.makeText(this, "signIn:Failure", Toast.LENGTH_SHORT).show()
            }
        }
    }
    companion object{
        const val TAG = "MainActivity"
    }
}
