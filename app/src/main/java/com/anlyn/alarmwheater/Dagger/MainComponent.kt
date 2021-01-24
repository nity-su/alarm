package com.anlyn.alarmwheater.Dagger

import android.content.Context
import com.anlyn.alarmwheater.Dagger.data.DataModule
import com.anlyn.alarmwheater.presentation.ui.DisplayAlarmActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
 interface MainComponent{
    fun inject(mainActivity: DisplayAlarmActivity)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun getContext(context: Context):Builder

        fun build():MainComponent
    }
}