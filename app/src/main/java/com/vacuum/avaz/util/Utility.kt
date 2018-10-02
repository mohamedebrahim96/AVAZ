package com.vacuum.avaz.util
import android.app.Activity
import android.content.Intent
import com.vacuum.avaz.R
import android.content.Context.MODE_PRIVATE
import com.vacuum.avaz.main.MainActivity
import java.util.*

class Utility {
    private var sTheme: Int = 0
    private var  slangage:String = "en"
    val MY_PREFS_NAME = "avaz"
    val Light_Theme = 0
    val Dark_Theme = 1
    val BBC_Theme = 2

    fun changeToTheme(activity: Activity, theme: Int) {
        sTheme = theme
        activity.finish()
        activity.startActivity(Intent(activity, MainActivity::class.java))
        activity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out)
    }
    fun onActivityCreateSetTheme(activity: Activity) {
        val prefs = activity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
        sTheme = prefs.getInt("theme", 0)
        slangage = prefs.getString("language", "en")

        when (sTheme) {
            Light_Theme -> activity.setTheme(R.style.Light_Theme)
            Dark_Theme -> activity.setTheme(R.style.Dark_Theme)
            BBC_Theme -> activity.setTheme(R.style.BBC_Theme)
            else -> activity.setTheme(R.style.Light_Theme)
        }
        //language
        //==============================================
        val res = activity.getResources()
        val dm = res.getDisplayMetrics()
        val conf = res.getConfiguration()
        conf.setLocale(Locale(slangage))
        // API 17+ only.
        res.updateConfiguration(conf, dm)

    }
}