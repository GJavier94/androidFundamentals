package eu.example.informationbook.Adapters

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import eu.example.informationbook.Fragments.FragmentFrance
import eu.example.informationbook.Fragments.FragmentMexico
import eu.example.informationbook.Fragments.FragmentUnitedKingdom
class ViewPagerAdapterCountries : FragmentStateAdapter {
    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager,
        lifecycle
    )


    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment? = null
        Log.i("ViewPagerAdapterCountry",position.toString())
        when(position){
            0 -> fragment = FragmentUnitedKingdom.newInstance()
            1 -> fragment = FragmentFrance.newInstance()
            2 -> fragment = FragmentMexico.newInstance()
        }

        return fragment!!
    }

    override fun getItemCount(): Int {
        return 3
    }
}
