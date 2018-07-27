package com.vacuum.avaz.Utils

import android.app.Application
import com.vacuum.avaz.R
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/airbnb.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

}