package com.anlyn.domain.Repository

import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.*

interface LocalRepository {

    fun getAll():Observable<List<AlarmEntity>>
    fun addAlarm(alarmEntity: AlarmEntity)
    fun remove(alarmEntity: AlarmEntity)
}