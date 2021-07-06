package eu.example.notes

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData

class NoteRepository {

    var noteDao:NoteDao
    var notes: LiveData<List<Note>>

    constructor(application: Application) {
        val database = NoteDatabase.getInstance(application)
        this.noteDao = database.getNoteDao()
        this.notes = noteDao.getAllNotes()

    }

    fun insert(note:Note){
        object :AsyncTask<Note, Unit, Unit>() {
            override fun doInBackground(vararg params: Note?) {
                Log.d("NoteRepo", note.toString() + "")
                noteDao?.Insert(note)
            }
        }.execute()
    }
    fun update(note: Note){
        object :AsyncTask<Note, Unit, Unit>() {
            override fun doInBackground(vararg params: Note?) {
                Log.d("NoteRepo", note.toString() + "")
                noteDao?.Update(note)
            }
        }.execute()
    }

    fun delete(note:Note){
        object :AsyncTask<Note, Unit, Unit>() {
            override fun doInBackground(vararg params: Note?) {
                Log.d("NoteRepo", note.toString() + "")
                noteDao?.Delete(note)
            }
        }.execute()
    }
    fun getAllNotes() :LiveData<List<Note>>{
        return notes
    }


}


