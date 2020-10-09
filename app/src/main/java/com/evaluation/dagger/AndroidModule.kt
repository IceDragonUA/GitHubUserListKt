package com.evaluation.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AndroidModule {

    @Provides
    @Singleton
    fun context(app: Application): Context {
        return app.applicationContext
    }

}