package com.vacuum.avaz.Utils

import android.app.Application


class ThemeApplication : Application() {
    companion object {
        // App level variable to retain selected spinner value
        var currentPosition: Int = 0
    }
}