package eu.example.notes.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.example.notes.Note
import eu.example.notes.R



class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    protected var listener: NoteAdapter.OnItemClickListener? = null
    private var notes: List<Note> = ArrayList<Note>()

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        val textViewDescription = itemView.findViewById<TextView>(R.id.textViewDescription)
        val item = itemView.setOnClickListener( View.OnClickListener {
            val position = adapterPosition
            if(listener != null && position != RecyclerView.NO_POSITION){
                listener?.onItemClick(notes[position])
            }
        })

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent,false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note: Note = notes[position]
        holder.textViewTitle.text = note.title
        holder.textViewDescription.text = note.description
    }

    override fun getItemCount(): Int {
        return notes.size
    }
    fun setNotes( notes:List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
    fun getNote(position: Int):Note{
        return this.notes[position]
    }

    interface OnItemClickListener{
        fun onItemClick(note:Note)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.listener = onItemClickListener
    }
}