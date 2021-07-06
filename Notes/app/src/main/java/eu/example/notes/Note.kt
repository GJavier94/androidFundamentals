package eu.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "note_table")
class Note: Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int = 0
    @ColumnInfo(name = "title") @NotNull
    var title:String = ""
    @ColumnInfo(name = "description") @NotNull
    var description:String = ""

    constructor(title: String, description: String) {
        this.title = title
        this.description = description
    }

}