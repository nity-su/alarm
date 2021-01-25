package com.anlyn.alarmwheater.mapper

import com.anlyn.alarmwheater.presentation.entities.Alarm
import com.anlyn.domain.Mapper
import com.anlyn.domain.models.AlarmEntity

class AlarmEntityAlarmMapper : Mapper<AlarmEntity,Alarm>() {
    override fun mapFrom(from: AlarmEntity): Alarm {
        TODO("Not yet implemented")
    }
}