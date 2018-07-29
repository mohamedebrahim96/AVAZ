package com.vacuum.avaz.di.module

import com.vacuum.avaz.api.ApiServiceInterface
import com.vacuum.avaz.ui.list.ListContract
import com.vacuum.avaz.ui.list.ListPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by ogulcan on 07/02/2018.
 */
@Module
class FragmentModule {

   /* @Provides
    fun provideAboutPresenter(): AboutContract.Presenter {
        return AboutPresenter()
    }*/

    @Provides
    fun provideListPresenter(): ListContract.Presenter {
        return ListPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}