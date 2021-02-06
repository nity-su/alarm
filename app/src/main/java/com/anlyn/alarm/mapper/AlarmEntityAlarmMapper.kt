package com.anlyn.alarm.mapper

import com.anlyn.alarm.presentation.entities.Alarm
import com.anlyn.domain.Mapper
import com.anlyn.domain.models.AlarmEntity

class AlarmEntityAlarmMapper : Mapper<AlarmEntity,Alarm>() {
    override fun mapFrom(from: AlarmEntity): Alarm {
        TODO("Not yet implemented")
    }
}