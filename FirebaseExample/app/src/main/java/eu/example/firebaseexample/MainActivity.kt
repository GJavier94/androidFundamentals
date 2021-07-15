package eu.example.firebaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    private var textView: MaterialTextView? = null
    private var editTextName: EditText? = null
    private var dataBase= FirebaseDatabase.getInstance()
    private var dbReferenceUsers = dataBase.reference.child("Users")
    private var dbReference = dataBase.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextName = findViewById(R.id.editTextName)
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        dbReference.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("Users").child("UserName").value as String
                textView?.setText(name)
            }
            override fun onCancelled(error: DatabaseError) {
                textView?.setText("not connection to the server")
            }
        })

        button?.setOnClickListener {
            val name = editTextName?.text.toString()
            dbReferenceUsers.child("UserName").setValue(name)

        }
    }
}