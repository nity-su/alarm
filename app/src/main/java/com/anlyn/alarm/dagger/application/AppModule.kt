package com.anlyn.alarm.dagger.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule{
    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun providesAppContext(application: Application): Context {
            return application.applicationContext
        }
    }
}