package eu.example.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val data: List<ModelClass>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(@NonNull itemView: View): RecyclerView.ViewHolder(itemView) {
        var textView1: TextView = itemView.findViewById(R.id.textView1)
        var textView2: TextView = itemView.findViewById(R.id.textView2)
        var textView3: TextView = itemView.findViewById(R.id.textView3)
        var textView4: TextView = itemView.findViewById(R.id.textView4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView1.text = data[position].userId
        holder.textView2.text = data[position].id
        holder.textView3.text = data[position].title
        holder.textView4.text = data[position].subtitle

    }

    override fun getItemCount(): Int {
        return data.size
    }
}





