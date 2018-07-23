package com.vacuum.avaz

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vacuum.avaz.Fragments.SecondFragment
import com.vacuum.avaz.Fragments.ThirdFragment
import me.eijaz.tabstutorial.FirstFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            3 -> FirstFragment()
            4 -> SecondFragment()
            5 -> ThirdFragment()
            6 -> FirstFragment()
            else -> {
                return ThirdFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 10
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Top Stories"
            1 -> "World"
            2 -> "Technology"
            3 -> "culture"
            4 -> "business"
            5 -> "health"
            6 -> "sports"
            7 -> "environment"
            8 -> "religion"
            else -> {
                return "Video"
            }
        }
    }

}