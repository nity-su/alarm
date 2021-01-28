package com.anlyn.alarmwheater.Dagger

import android.content.Context
import com.anlyn.alarmwheater.Dagger.data.DataModule
import com.anlyn.alarmwheater.Dagger.display_alarm.DisplayAlarmModule
import com.anlyn.alarmwheater.Dagger.display_alarm.DisplayAlarmSubComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
 interface MainComponent{
//    fun inject(mainActivity: DisplayAlarmActivity)
@Singleton
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun getContext(context: Context):Builder
        @BindsInstance
        fun getDataModule(dataModule: DataModule):Builder

        fun build():MainComponent
    }

        fun displaySubComponent(displayAlarmModule: DisplayAlarmModule):DisplayAlarmSubComponent

}