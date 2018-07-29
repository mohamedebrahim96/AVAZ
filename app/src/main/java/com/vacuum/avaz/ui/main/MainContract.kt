package com.vacuum.avaz

import com.vacuum.avaz.ui.base.BaseContract

/**
 * Created by ogulcan on 07/02/2018.
 */
class MainContract {

    interface View: BaseContract.View {
        fun showAboutFragment()
        fun showListFragment()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
        fun onDrawerOptionAboutClick()
    }
}