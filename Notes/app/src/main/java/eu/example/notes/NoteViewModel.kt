package eu.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class NoteViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var noteRepository:NoteRepository
    lateinit var notes: LiveData<List<Note>>
    init{
        noteRepository = NoteRepository(application)
        notes = noteRepository.getAllNotes()
    }
    fun insert(note:Note){
        noteRepository.insert(note)
    }
    fun update(note:Note){
        noteRepository.update(note)
    }
    fun delete(note:Note){
        noteRepository.delete(note)
    }

    fun getAllNotes():LiveData<List<Note>>{
        return noteRepository.getAllNotes()
    }
}


