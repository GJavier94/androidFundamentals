package eu.example.notes.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import eu.example.notes.Note
import eu.example.notes.R

class UpdateActivity : AppCompatActivity() {
    private var note:Note? = null
    private var noteId:Int = 0
    private var buttonCancel: Button? = null
    private var buttonSave: Button? = null
    private var editTextDescription: EditText? = null
    private var editTextTitle: EditText?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        supportActionBar?.setTitle("Update note")

        getComponentsFromView()
        buttonSave = findViewById<Button>(R.id.buttonSaveUpdate)
        buttonCancel = findViewById<Button>(R.id.buttonCancelUpdate)

        this.note = getData()
        setDataIntoView(note)

        buttonCancel?.setOnClickListener( View.OnClickListener {
                v: View ->
            Toast.makeText(this, "Nothing Updated", Toast.LENGTH_SHORT).show()
            this.setResult(RESULT_CANCELED)
            this.finish()
        })
        buttonSave?.setOnClickListener(  View.OnClickListener {
                v: View ->
            this.note = getDataFromView()
            if(!this.note!!.title.isEmpty() && !this.note!!.description.isEmpty()){
                UpdateNote(this.note!!)
            }else{
                Toast.makeText(this.applicationContext, "Some of the fields are empty", Toast.LENGTH_SHORT)
            }
        })
    }

    private fun getDataFromView(): Note? {
        val noteTitle = editTextTitle?.text.toString()
        val noteDescription = editTextDescription?.text.toString()

        return Note(noteTitle,noteDescription)
    }

    private fun getComponentsFromView() {
        editTextTitle = findViewById<EditText>(R.id.editTextTitleUpdate)
        editTextDescription = findViewById<EditText>(R.id.editTextDescriptionUpdate)
    }

    fun getData():Note?{
        val note = intent.getSerializableExtra("note") as Note?
        this.noteId = note!!.id
        return note
    }
    private fun UpdateNote(note: Note) {
        note.id = this.noteId
        if(note.id != -1){
            intent.putExtra("note",note)
            setResult(RESULT_OK, intent)
            finish()
        }
        setResult(RESULT_CANCELED)
        finish()
    }


    private fun setDataIntoView(note: Note?) {
        editTextTitle?.setText(note?.title)
        editTextDescription?.setText(note?.description)
    }
}