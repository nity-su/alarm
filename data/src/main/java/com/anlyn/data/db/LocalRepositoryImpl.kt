package com.anlyn.data.db

import com.anlyn.data.db.local.RoomAlarmDataSource
import com.anlyn.domain.Repository.LocalRepository
import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class LocalRepositoryImpl (val datasource: RoomAlarmDataSource) : LocalRepository {
    override fun getAll(): Observable<List<AlarmEntity>> {
        return datasource.getAll()
    }

    override fun addAlarm(alarmEntity: AlarmEntity) {
        datasource.save(alarmEntity)
    }

    override fun remove(alarmEntity: AlarmEntity) {
        datasource.remove(alarmEntity)
    }
}