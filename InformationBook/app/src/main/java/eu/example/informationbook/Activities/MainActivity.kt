package eu.example.informationbook.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.example.informationbook.Adapters.AdapterClass
import eu.example.informationbook.ModelClass
import eu.example.informationbook.R

class MainActivity : AppCompatActivity() {
    private var adapter: AdapterClass? = null
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = GridLayoutManager(this, 2 )
        val arrayModelClass: ArrayList<ModelClass> = ArrayList<ModelClass>()

        val mc1 = ModelClass("countries", "The Countries")
        val mc2 = ModelClass("leaders", "The Leaders")
        val mc3 = ModelClass("museums", "The Museums")
        val mc4 = ModelClass("wonders", "Seven wonders of the world")
        arrayModelClass.add(mc1)
        arrayModelClass.add(mc2)
        arrayModelClass.add(mc3)
        arrayModelClass.add(mc4)
        adapter = AdapterClass(arrayModelClass, this)
        recyclerView?.adapter = adapter
    }


}