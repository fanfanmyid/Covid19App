@file:Suppress("UNREACHABLE_CODE")

package com.fandlys.covid19.view.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fandlys.covid19.R
import com.fandlys.covid19.view.fragment.CaseFragment
import com.fandlys.covid19.view.fragment.ProvinceFragment
import com.flarebit.flarebarlib.FlareBar
import com.flarebit.flarebarlib.Flaretab
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        openFragment(ProvinceFragment())
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_main, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }


    private fun initUi() {
        val bottomBar = findViewById<FlareBar>(R.id.bottomBar)
        bottomBar.barBackgroundColor = Color.parseColor("#393e46")
        val tabs = ArrayList<Flaretab>()
        tabs.add(
            Flaretab(
               getDrawable(R.drawable.baseline_location_city_white_18dp),
                getString(R.string.provinsi),
                //resources.getString(R.string.blank),
                "#D65A31"
            )
        )

        tabs.add(
            Flaretab(
                getDrawable(R.drawable.baseline_accessibility_white_18dp),
                getString(R.string.kasus),
                //resources.getString(R.string.blank),
                "#D65A31"

            )
        )

//        tabs.add(
//            Flaretab(
//                getDrawable(R.drawable.baseline_assignment_ind_white_18dp),
//                getString(R.string.harian),
//                //resources.getString(R.string.blank),
//                "#D65A31"
//            )
//        )

        bottomBar.tabList = tabs
        bottomBar.attachTabs(this@MainActivity)
        bottomBar.setTabChangedListener { selectedTab, selectedIndex, oldIndex ->
            //tabIndex starts from 0 (zero). Example : 4 tabs = last Index - 3
            when (selectedIndex) {
                0 -> openFragment(ProvinceFragment())
                1 -> openFragment(CaseFragment())

            }
        }

    }
}
