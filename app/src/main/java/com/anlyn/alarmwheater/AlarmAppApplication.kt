package com.anlyn.alarmwheater

import android.app.Application
import com.anlyn.alarmwheater.Dagger.DaggerMainComponent
import com.anlyn.alarmwheater.Dagger.MainComponent
import com.anlyn.alarmwheater.Dagger.data.DataModule
import com.anlyn.alarmwheater.Dagger.display_alarm.DisplayAlarmModule
import com.anlyn.alarmwheater.Dagger.display_alarm.DisplayAlarmSubComponent

class AlarmAppApplication : Application() {
    lateinit var mainComponent: MainComponent
    lateinit var displayAlarmSubComponent: DisplayAlarmSubComponent
    override fun onCreate() {
        super.onCreate()
        initComponent()
    }
    fun initComponent(){
        mainComponent = DaggerMainComponent.builder()
            .getContext(this)
            .getDataModule(DataModule())
            .build()
    }

    fun createDisplayAlarmSubComponent():DisplayAlarmSubComponent{
        displayAlarmSubComponent = mainComponent.displaySubComponent(DisplayAlarmModule())
        return displayAlarmSubComponent
    }
}