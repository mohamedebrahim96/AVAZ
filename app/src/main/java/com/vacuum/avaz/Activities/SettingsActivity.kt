package com.vacuum.avaz.Activities

import android.content.Context
import android.widget.AdapterView
import android.widget.Spinner
import android.os.Bundle
import android.view.View
import com.vacuum.avaz.ContextActivity
import com.vacuum.avaz.R
import com.vacuum.avaz.Utils.Utility



class SettingsActivity : ContextActivity() {
    private var spThemes: Spinner? = null
    val MY_PREFS_NAME = "avaz"
    var sTheme =0

    // Here we set the theme for the activity
    // Note `Utils.onActivityCreateSetTheme` must be called before `setContentView`
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // MUST BE SET BEFORE setContentView
        Utility().onActivityCreateSetTheme(this)
        // AFTER SETTING THEME
        setContentView(R.layout.activity_settings)
        setupSpinnerItemSelection()
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

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }
}