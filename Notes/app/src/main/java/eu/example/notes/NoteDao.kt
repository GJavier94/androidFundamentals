package eu.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

//CRUD  create read update delete

@Dao
interface NoteDao {
    @Insert
    fun Insert(note:Note)
    @Update
    fun Update(note:Note)
    @Delete
    fun Delete(note:Note)
    //then functions to read -> queries

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNotes():LiveData<List<Note>>

}