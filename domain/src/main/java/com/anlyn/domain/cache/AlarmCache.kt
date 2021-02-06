package com.anlyn.domain.cache

import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable

public interface AlarmCache{

    fun save(alarmEntity: AlarmEntity)
    fun get(alarmID:Int):Observable<AlarmEntity>
    fun getAll():Observable<List<AlarmEntity>>
    fun remove(alarmEntity: AlarmEntity)
}