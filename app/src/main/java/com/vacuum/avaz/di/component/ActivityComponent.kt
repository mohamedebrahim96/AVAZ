package com.vacuum.avaz.di.component

import com.vacuum.avaz.di.module.ActivityModule
import com.vacuum.avaz.main.MainActivity
import dagger.Component

/**
 * Created by ogulcan on 07/02/2018.
 */
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}