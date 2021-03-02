package com.anlyn.domain.usercase

import com.anlyn.domain.Repository.LocalRepository
import com.anlyn.domain.Transformer
import com.anlyn.domain.cache.AlarmMangerHelper
import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable


class RemoveAlarmUseCase(private val localRepo: LocalRepository,private val alarmMangerHelper: AlarmMangerHelper,
                         transformer: Transformer<Boolean>) : UseCase<Boolean>(transformer){
    companion object {
        private const val PARAM_ALARM_ENTITY = "AlarmEntity"
    }
    override fun createObservable(data: Map<String, Any?>?): Observable<Boolean> {
        val alarmEntity = data?.get(PARAM_ALARM_ENTITY) as AlarmEntity
        alarmEntity.let {
            return Observable.fromCallable {
                alarmMangerHelper.cancelAlarm(alarmEntity)
                localRepo.remove(it)
                return@fromCallable true
            }
        }
    }
    fun remove(alarmEntity:AlarmEntity) : Observable<Boolean>{
        val map = HashMap<String,AlarmEntity>()
        map[PARAM_ALARM_ENTITY] = alarmEntity
        return observable(map)
    }
}