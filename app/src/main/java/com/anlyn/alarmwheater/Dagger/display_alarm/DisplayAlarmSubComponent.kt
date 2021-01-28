package com.anlyn.alarmwheater.Dagger.display_alarm

import com.anlyn.alarmwheater.presentation.ui.displayalarm.DisplayAlarmActivity
import dagger.Subcomponent

@AlarmActivityScope
@Subcomponent(modules = [DisplayAlarmModule::class])
interface DisplayAlarmSubComponent {
    fun inject(displayAlarmActivity: DisplayAlarmActivity)
}