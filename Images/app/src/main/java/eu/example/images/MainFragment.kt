package eu.example.images

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment


class MainFragment : ListFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        //while the view is being created we will load the item list
        //Creating the item list involves
        //1.- create an array adapter (using the adapter pattern)
        // it binds data source with component
        // 2.- bind ArrayAdapter to the list view
        var arrayAdapter = ArrayAdapter.createFromResource(
            container!!.context,
            R.array.countries,
            android.R.layout.simple_list_item_1
        )
        listAdapter = arrayAdapter


        return view
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        //when an item is clicked a new activity is created giving it the info pos to the act

        /*val intent = Intent( activity,SecondActivity::class.java  )
        intent.putExtra("position", position)
        startActivity(intent)
        */
        val fragmentContentCountry = Fragment_content_country()
        val bundle = Bundle()
        bundle.putInt("position", position)
        fragmentContentCountry.arguments = bundle

        var fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.main_frame, fragmentContentCountry)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()


    }


}