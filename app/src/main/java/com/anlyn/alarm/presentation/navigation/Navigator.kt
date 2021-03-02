package com.anlyn.alarm.presentation.navigation

import androidx.appcompat.app.AppCompatActivity
import com.anlyn.alarm.presentation.ui.alarmsetting.SettingActivity
import com.anlyn.domain.models.AlarmEntity
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun navigatorAlarmSetting(activity: AppCompatActivity, alarmEntity: AlarmEntity?,requestCode:Int){
        val intent = SettingActivity.getCallingIntent(activity,alarmEntity)
        activity.startActivityForResult(intent,requestCode)
    }
}