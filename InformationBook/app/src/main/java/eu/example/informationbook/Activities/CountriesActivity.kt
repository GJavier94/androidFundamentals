package eu.example.informationbook.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import eu.example.informationbook.Adapters.ViewPagerAdapterCountries
import eu.example.informationbook.R

class CountriesActivity : AppCompatActivity() {


    private var viewPagerCountries: ViewPager2? = null
    private var tabLayoutCountries: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries_acitivity)

        tabLayoutCountries = findViewById<TabLayout>(R.id.TabLayoutCountries)
        viewPagerCountries = findViewById<ViewPager2>(R.id.viewPagerCountries)

        val adapter = ViewPagerAdapterCountries(supportFragmentManager, lifecycle)
        viewPagerCountries?.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(
            tabLayoutCountries!!,
            viewPagerCountries!!,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when(position){
                    0 -> tab.text = "United Kingdom"
                    1 -> tab.text = "France"
                    2 -> tab.text = "Mexico"
                }
            }
        )
        tabLayoutMediator.attach()
    }
}