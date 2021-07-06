package eu.example.informationbook

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import eu.example.informationbook.R

class CardViewHolder : RecyclerView.ViewHolder {

    var cardView: CardView?
    var textViewSplash: TextView?
    var imageViewSplash: ImageView? = null

    constructor(itemView: View) : super(itemView){
        imageViewSplash = itemView.findViewById<ImageView>(R.id.imageViewSplash)
        textViewSplash = itemView.findViewById<TextView>(R.id.textViewSplash)
        cardView = itemView.findViewById<CardView>(R.id.cardView)
    }




}