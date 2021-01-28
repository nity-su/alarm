package com.anlyn.data.mapper
import com.anlyn.data.entities.AlarmData
import com.anlyn.domain.Mapper
import com.anlyn.domain.models.AlarmEntity
import javax.inject.Inject
import javax.inject.Singleton


class AlarmEntityDataMapper @Inject constructor() : Mapper<AlarmEntity,AlarmData>(){
    override fun mapFrom(from: AlarmEntity): AlarmData {
        return AlarmData(id = from.id,
        time = from.time,
        mon = from.mon,
        teu = from.teu,
        wed = from.wed,
        thu = from.thu,
        fri = from.fri,
        sat = from.sat,
        sun = from.sun)
    }
}