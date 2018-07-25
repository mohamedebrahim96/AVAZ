package com.vacuum.avaz.Activities

import android.widget.AdapterView
import com.vacuum.avaz.Utils.ThemeApplication
import android.widget.Spinner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.vacuum.avaz.ContextActivity
import com.vacuum.avaz.MainActivity
import com.vacuum.avaz.R
import com.vacuum.avaz.Utils.Utility

class SettingsActivity : ContextActivity() {
    private var spThemes: Spinner? = null
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
        spThemes!!.setSelection(ThemeApplication.currentPosition)
        ThemeApplication.currentPosition = spThemes!!.selectedItemPosition

        spThemes!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View,
                                        position: Int, id: Long) {
                if (ThemeApplication.currentPosition != position) {
                    Utility().changeToTheme(this@SettingsActivity, position)
                }
                ThemeApplication.currentPosition = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }
}