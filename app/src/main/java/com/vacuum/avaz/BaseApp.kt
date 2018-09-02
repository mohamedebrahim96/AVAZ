package com.vacuum.avaz

import android.app.Application
import com.bumptech.glide.Glide.setup
import com.vacuum.avaz.di.component.ApplicationComponent
import com.vacuum.avaz.di.module.ApplicationModule

/**
 * Created by ogulcan on 07/02/2018.
 */
class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        //setup()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }
    }

    /*fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }*/

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }







}