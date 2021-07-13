package eu.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {


    private var buttonRegister: Button? = null
    private var editText_signUp_password: EditText? = null
    private var editText_signUp_Email: EditText? = null
    private var fireBaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        editText_signUp_Email = findViewById<EditText>(R.id.editText_signUp_Email)
        editText_signUp_password = findViewById<EditText>(R.id.editText_signUp_password)
        buttonRegister = findViewById<Button>(R.id.buttonRegister)


        buttonRegister?.setOnClickListener{
            val email:String = editText_signUp_Email?.text.toString()
            val password:String = editText_signUp_password?.text.toString()
            signUpFirebase(email,password )
        }

    }

    private fun signUpFirebase(email: String, password: String) {
        fireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                //signIn is succesful
                Log.d(TAG  , "createUserWithEmailAndPassword:Success"  )
                val user = fireBaseAuth.currentUser
                Toast.makeText(this, "signup:Success", Toast.LENGTH_SHORT).show()
                updateUI(user)
            }else{
                Log.d(TAG  , "createUserWithEmailAndPassword:Failure"  )
                Toast.makeText(this, "signup:Failure", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this.applicationContext, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        private const val TAG = "EMAIL_PASSWORD"
    }
}

