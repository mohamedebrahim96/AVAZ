package com.vacuum.avaz.di.component

import com.vacuum.avaz.di.module.FragmentModule
import com.vacuum.avaz.ui.list.ListFragment
import dagger.Component

/**
 * Created by ogulcan on 07/02/2018.
 */
@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    //fun inject(aboutFragment: AboutFragment)

    fun inject(listFragment: ListFragment)

}