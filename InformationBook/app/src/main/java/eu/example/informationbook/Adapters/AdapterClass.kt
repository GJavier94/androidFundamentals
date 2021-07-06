package eu.example.informationbook.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.example.informationbook.Activities.CountriesActivity
import eu.example.informationbook.CardViewHolder
import eu.example.informationbook.ModelClass
import eu.example.informationbook.R

class AdapterClass: RecyclerView.Adapter<CardViewHolder>  {
    private val modelList:ArrayList<ModelClass>
    private val context:Context

    constructor(modelList: ArrayList<ModelClass>, context: Context) {
        this.modelList = modelList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_design,parent,false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int){
        val model = modelList[position]
        holder.textViewSplash?.text = model.categoryName
        holder.imageViewSplash?.setImageResource(context.resources.getIdentifier(model.imageName, "drawable", context.packageName))
        holder.cardView?.setOnClickListener{
            if(position % 4 == 0 ){
                val intent = Intent( context.applicationContext,   CountriesActivity::class.java )
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }


}