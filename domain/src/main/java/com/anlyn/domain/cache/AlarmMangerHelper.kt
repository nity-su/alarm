package com.anlyn.domain.cache

import com.anlyn.domain.models.AlarmEntity

interface AlarmMangerHelper {
    fun startAlarm(alarmEntity: AlarmEntity)
    fun cancelAlarm(alarmEntity: AlarmEntity)
}