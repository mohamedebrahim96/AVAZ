package com.vacuum.avaz.di.component

import com.vacuum.avaz.BaseApp
import com.vacuum.avaz.di.module.ApplicationModule
import dagger.Component

/**
 * Created by ogulcan on 07/02/2018.
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}