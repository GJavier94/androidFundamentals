package eu.example.notes.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import eu.example.notes.R

class AddNoteActivity : AppCompatActivity() {

    private var buttonCancel: Button? = null
    private var buttonSave: Button? = null
    private var editTextDescription: EditText? = null
    private var editTextTitle: EditText?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        supportActionBar?.setTitle("Add note")
        editTextTitle = findViewById<EditText>(R.id.editTextTitle)
        editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonCancel = findViewById<Button>(R.id.buttonCancel)

        buttonCancel?.setOnClickListener( View.OnClickListener {
                v:View ->
                Toast.makeText(this, "Nothing Saved", Toast.LENGTH_SHORT).show()
                this.setResult(RESULT_CANCELED)
                this.finish()
        })
        buttonSave?.setOnClickListener(  View.OnClickListener {
            v:View ->
            val noteTitle = editTextTitle?.text.toString()
            val noteDescription = editTextDescription?.text.toString()

            if(!noteTitle.isEmpty() && !noteDescription.isEmpty()){
                saveNote(noteTitle, noteDescription)
            }else{
                Toast.makeText(applicationContext, "Some of the fields are empty", Toast.LENGTH_SHORT)
            }

        })

    }

    private fun saveNote(noteTitle: String, noteDescription: String) {
        val intent = Intent()
        intent.putExtra("noteTitle", noteTitle)
        intent.putExtra("noteDescription", noteDescription)
        setResult(RESULT_OK, intent)
        finish()
    }
}
