package eu.example.notes.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.example.notes.Adapters.NoteAdapter
import eu.example.notes.Note
import eu.example.notes.NoteViewModel
import eu.example.notes.R
import eu.example.notes.utilities.REQUEST_MAIN_ACT_ADD
import eu.example.notes.utilities.REQUEST_MAIN_ACT_UPD

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //adding the recycler view
        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        var noteAdapter: NoteAdapter = NoteAdapter()
        recyclerView.adapter = noteAdapter
        //getting the modelview
        noteViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(NoteViewModel::class.java)
        val observer:Observer<List<Note>> = Observer<List<Note>>(){ noteAdapter.setNotes(it) }
        noteViewModel.getAllNotes().observe(this, observer)

        ItemTouchHelper(object  : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or( ItemTouchHelper.RIGHT) ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(noteAdapter.getNote(viewHolder.adapterPosition))
            }

        }
        ).attachToRecyclerView(recyclerView)

        noteAdapter.setOnItemClickListener( object: NoteAdapter.OnItemClickListener{
            override fun onItemClick(note: Note) {
                val intent = Intent( applicationContext, UpdateActivity::class.java)
                intent.putExtra("id", note.id )
                intent.putExtra("title", note.title)
                intent.putExtra("description", note.description)

                intent.putExtra("note", note)
                startActivityForResult(intent, REQUEST_MAIN_ACT_UPD )
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.new_menu -> {
                val intent = Intent(this, AddNoteActivity::class.java )
                startActivityForResult(intent,REQUEST_MAIN_ACT_ADD)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_MAIN_ACT_ADD -> {
                when (resultCode) {
                    RESULT_CANCELED -> Log.i("MA", "addingNote canceled")
                    RESULT_OK -> {
                        Log.i("MA", "adding note...")
                        val noteTitle: String? = data?.getStringExtra("noteTitle")
                        val noteDescription: String? = data?.getStringExtra("noteDescription")
                        val note = Note(noteTitle!!, noteDescription!!)
                        noteViewModel.insert(note)
                    }
                }
            }
            REQUEST_MAIN_ACT_UPD -> {
                when(resultCode) {
                    RESULT_CANCELED -> Log.i("MA", "Updating note canceled")
                    RESULT_OK -> {
                        Log.i("MA", "Updating note...")
                        val note: Note? = data?.getSerializableExtra("note") as Note?

                        noteViewModel.update(note!!)
                    }
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}