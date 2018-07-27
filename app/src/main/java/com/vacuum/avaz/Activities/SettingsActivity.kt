package com.vacuum.avaz.Activities

import android.content.Context
import android.widget.AdapterView
import android.widget.Spinner
import android.os.Bundle
import android.view.View
import com.vacuum.avaz.ContextActivity
import com.vacuum.avaz.R
import com.vacuum.avaz.Utils.Utility
import android.content.Intent
import android.util.DisplayMetrics
import java.util.*


class SettingsActivity : ContextActivity() {
    private var spThemes: Spinner? = null
    private var splanguages: Spinner? = null

    val MY_PREFS_NAME = "avaz"
    var sTheme =0
    var myLocale:Locale?=null
    var slangage:Int?=null

    // Here we set the theme for the activity
    // Note `Utils.onActivityCreateSetTheme` must be called before `setContentView`
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // MUST BE SET BEFORE setContentView
        Utility().onActivityCreateSetTheme(this)
        // AFTER SETTING THEME
        setContentView(R.layout.activity_settings)
        setupSpinnerItemSelection()
        setupSpinner2()
    }

    private fun setupSpinner2() {
        splanguages = findViewById(R.id.splanguages) as Spinner
        val prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE)
        slangage = prefs.getInt("language", 0)
        splanguages!!.setSelection(slangage!!)
        splanguages!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View,
                                        position: Int, id: Long) {

                setLocale(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupSpinnerItemSelection() {
        spThemes = findViewById(R.id.spThemes) as Spinner
        val prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE)
        sTheme = prefs.getInt("theme", 0)
        spThemes!!.setSelection(sTheme)
        sTheme = spThemes!!.selectedItemPosition
        spThemes!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View,
                                        position: Int, id: Long) {
                if (sTheme != position) {
                Utility().changeToTheme(this@SettingsActivity, position)
                }
                sTheme = position
                val editor = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit()
                editor.putInt("theme", position)
                editor.apply()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    fun setLocale(lang: String) {
        val editor = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putString("language", lang)
        editor.apply()
        when(lang){
            0 -> myLocale = Locale("en")
            1 -> myLocale = Locale("es")
            2 -> myLocale = Locale("fr")

        }
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(this, SettingsActivity::class.java)
        startActivity(refresh)
    }
}