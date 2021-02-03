package com.anlyn.alarmwheater

import android.app.Activity
import android.app.Application
import com.anlyn.alarmwheater.Dagger.androidInjection.DaggerMainComponent
import com.anlyn.alarmwheater.Dagger.androidInjection.MainComponent
import com.anlyn.alarmwheater.Dagger.data.DataModule
import com.anlyn.alarmwheater.Dagger.display_alarm.DisplayAlarmModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AlarmAppApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerMainComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector



}