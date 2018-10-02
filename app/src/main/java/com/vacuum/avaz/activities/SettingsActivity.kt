package com.vacuum.avaz.activities

import android.content.Context
import android.widget.AdapterView
import android.widget.Spinner
import android.os.Bundle
import android.view.View
import com.vacuum.avaz.ContextActivity
import com.vacuum.avaz.R
import com.vacuum.avaz.util.Utility
import android.content.Intent
import com.vacuum.avaz.main.MainActivity


class SettingsActivity : ContextActivity() {
    private var spThemes: Spinner? = null
    private var splanguages: Spinner? = null
    val MY_PREFS_NAME = "avaz"
    var sTheme =0
    var slangage:String?=null
    var slangage2:Int?=null
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
        slangage = prefs.getString("language", "en")
        when(slangage){
            "en" -> slangage2 = 0
            "es" -> slangage2 = 1
            "fr" -> slangage2 = 2
            "ru" -> slangage2 = 3
            "de" -> slangage2 = 4
            "ja" -> slangage2 = 5
            "zh" -> slangage2 = 6
            "ar" -> slangage2 = 7
        }
        splanguages!!.setSelection(slangage2!!)
        splanguages!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View,
                                        position: Int, id: Long) {
                if (slangage2 != position) {
                    setLocale(position)
                }
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
    fun setLocale(lang: Int) {
        var lang_str: String? = "en"
        when(lang){
            0 -> lang_str = "en"
            1 -> lang_str = "es"
            2 -> lang_str = "fr"
            3 -> lang_str = "ru"
            4 -> lang_str = "de"
            5 -> lang_str = "ja"
            6 -> lang_str = "zh"
            7 -> lang_str = "ar"
        }
        val editor = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putString("language", lang_str)
        editor.apply()
        //=======================================
        finish()
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out)
    }
}