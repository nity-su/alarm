package com.anlyn.alarm.dagger.androidInjection

import com.anlyn.alarm.dagger.data.DataModule
import com.anlyn.alarm.dagger.display_alarm.DisplayAlarmModule
import com.anlyn.alarm.dagger.setting_alarm.SettingActivityScope
import com.anlyn.alarm.dagger.setting_alarm.SettingAlarmModule
import com.anlyn.alarm.presentation.ui.alarmsetting.SettingActivity
import com.anlyn.alarm.presentation.ui.displayalarm.DisplayAlarmActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [DataModule::class,DisplayAlarmModule::class])
    abstract fun contributesDisplayAlarmActivity():DisplayAlarmActivity

    @SettingActivityScope
    @ContributesAndroidInjector(modules = [DataModule::class,SettingAlarmModule::class])
    abstract fun contributesSettingAlarmActivity():SettingActivity
}