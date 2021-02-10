package com.anlyn.data.db.local

import com.anlyn.data.entities.AlarmData
import com.anlyn.domain.Mapper
import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RoomAlarmDataSource @Inject constructor(val database:AppDataBase,
                                             val entityDataMapper: Mapper<AlarmEntity, AlarmData>,
                                             val dataEntityMapper: Mapper<AlarmData,AlarmEntity>)
{
    private val dao = database.alarmDataDao()
    fun save(alarmEntity: AlarmEntity) {
        dao.insertAlarmData(entityDataMapper.mapFrom(alarmEntity))
    }

    fun get(alarmID: Int): Observable<AlarmEntity> {
        TODO("Not yet implemented")
    }

    fun getAll(): Observable<List<AlarmEntity>> {
        return Observable.fromCallable {(dao.getAllAlarmSetCondition()?.map { dataEntityMapper.mapFrom(it) })}
    }

    fun remove(alarmEntity: AlarmEntity) {
        dao.deleteAlarmData(entityDataMapper.mapFrom(alarmEntity))
    }
}