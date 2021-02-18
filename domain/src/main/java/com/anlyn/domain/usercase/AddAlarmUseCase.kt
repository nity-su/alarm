package com.anlyn.domain.usercase

import com.anlyn.domain.Repository.LocalRepository
import com.anlyn.domain.models.AlarmEntity
import com.anlyn.domain.Transformer
import com.anlyn.domain.cache.AlarmMangerHelper
import io.reactivex.rxjava3.core.Observable

class AddAlarmUseCase(transformer:Transformer<Boolean>
                        , private val localRepo: LocalRepository,private val alarmMangerHelper: AlarmMangerHelper
) : UseCase<Boolean>(transformer) {

    companion object {
        private const val PARAM_ALARM_ENTITY = "movieEntity"
    }

    override fun createObservable(data:Map<String,Any?>?): Observable<Boolean> {
        data.let {
            return Observable.fromCallable {
                val entity = data?.get(PARAM_ALARM_ENTITY) as AlarmEntity
                localRepo.addAlarm(entity)
                alarmMangerHelper.startAlarm(entity)
                return@fromCallable true
            }
        }
    }

    fun save(entity:AlarmEntity): Observable<Boolean> {
        val data = HashMap<String,AlarmEntity>()
        data[PARAM_ALARM_ENTITY] = entity
        return observable(data)
    }
}