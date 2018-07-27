package com.vacuum.avaz

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vacuum.avaz.Fragments.SecondFragment
import com.vacuum.avaz.Fragments.ThirdFragment
import me.eijaz.tabstutorial.FirstFragment

class MyPagerAdapter(fm: FragmentManager,val mContext: Context) : FragmentPagerAdapter(fm) {



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
            0 ->mContext.getResources().getString(R.string.Top_Stories)
            1 ->mContext.getResources().getString(R.string.World)
            2 ->mContext.getResources().getString(R.string.Technology)
            3 -> mContext.getResources().getString(R.string.culture)
            4 -> mContext.getResources().getString(R.string.business)
            5 -> mContext.getResources().getString(R.string.health)
            6 -> mContext.getResources().getString(R.string.sports)
            7 -> mContext.getResources().getString(R.string.environment)
            8 -> mContext.getResources().getString(R.string.religion)
            else -> {
                return mContext.getResources().getString(R.string.Video)
            }
        }
    }

}