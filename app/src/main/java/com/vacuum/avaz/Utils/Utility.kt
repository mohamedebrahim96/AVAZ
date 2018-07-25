package com.vacuum.avaz.Utils
import android.app.Activity
import android.content.Intent
import com.vacuum.avaz.Activities.SettingsActivity
import com.vacuum.avaz.R


class Utility
{
    private var sTheme: Int = 0

    val Light_Theme = 0
    val Dark_Theme = 1
    val BBC_Theme = 2

    fun changeToTheme(activity: Activity, theme: Int) {
        sTheme = theme
        activity.finish()
        activity.startActivity(Intent(activity, SettingsActivity::class.java))
        activity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out)
    }

    fun onActivityCreateSetTheme(activity: Activity) {
        when (sTheme) {
            Light_Theme -> activity.setTheme(R.style.Light_Theme)
            Dark_Theme -> activity.setTheme(R.style.Dark_Theme)
            BBC_Theme -> activity.setTheme(R.style.BBC_Theme)
            else -> activity.setTheme(R.style.Light_Theme)
        }
    }
}