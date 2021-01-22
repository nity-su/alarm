package com.anlyn.data.db.local

import com.anlyn.data.entities.AlarmData
import com.anlyn.data.mapper.AlarmDataEntityMapper
import com.anlyn.data.mapper.AlarmEntityDataMapper
import com.anlyn.domain.AlarmCache
import com.anlyn.domain.Mapper
import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RoomAlarmCache @Inject constructor(val database:AppDataBase,
                                             val entityDataMapper: Mapper<AlarmEntity, AlarmData>,
                                             val dataEntityMapper: Mapper<AlarmData,AlarmEntity>): AlarmCache {
    private val dao = database.alarmDataDao()
    override fun save(alarmEntity: AlarmEntity) {
        dao.insertAlarmData(entityDataMapper.mapFrom(alarmEntity))
    }

    override fun get(alarmID: Int): Observable<AlarmEntity> {
        TODO("Not yet implemented")
    }

    override fun getAll(): Observable<List<AlarmEntity>> {
        TODO("Not yet implemented")
    }

    override fun remove(alarmEntity: AlarmEntity) {
        dao.deleteAlarmData(entityDataMapper.mapFrom(alarmEntity))
    }
}