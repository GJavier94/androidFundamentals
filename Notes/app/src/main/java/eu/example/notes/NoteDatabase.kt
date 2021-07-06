package eu.example.notes

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import eu.example.notes.utilities.DATABASE_NAME

@Database(entities = [Note::class] , version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao():NoteDao

    companion object{

        @Volatile private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context):NoteDatabase{
            Log.i("ND/getInstance", "calling GetInstance")
            if(INSTANCE == null) {
                synchronized(this){
                    INSTANCE = buildDatabase(context)
                }

            }
            return INSTANCE!!

        }

        private fun buildDatabase(context: Context): NoteDatabase {



            return Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java,
                        DATABASE_NAME,)
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        PopulateDBAsyncTask(INSTANCE!!).execute()
                    }
                })
                .build()

        }


        class PopulateDBAsyncTask(noteDatabase: NoteDatabase) : AsyncTask<Unit, Unit, Unit>(){
            private var noteDao:NoteDao = noteDatabase.getNoteDao()
            override fun doInBackground(vararg params: Unit?) {
                noteDao.Insert(Note("Title 1 ", "Descripcion 1"))
                noteDao.Insert(Note("Title 2 ", "Descripcion 2"))
                noteDao.Insert(Note("Title 3 ", "Descripcion 3"))
                noteDao.Insert(Note("Title 4 ", "Descripcion 4"))
                noteDao.Insert(Note("Title 5 ", "Descripcion 5"))
            }
        }
    }


}