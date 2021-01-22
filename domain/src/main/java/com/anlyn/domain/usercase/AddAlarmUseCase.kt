package com.anlyn.domain.usercase

import com.anlyn.domain.AlarmCache
import com.anlyn.domain.models.AlarmEntity
import com.anlyn.domain.Transformer
import io.reactivex.rxjava3.core.Observable

class AddAlarmUseCase(transformer:Transformer<Boolean>
                        , private val alarmCache: AlarmCache) : UseCase<Boolean>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "movieEntity"
    }

    override fun createObservable(data:Map<String,Any?>?): Observable<Boolean> {
        data.let {
            return Observable.fromCallable {
                val entity = data?.get(PARAM_MOVIE_ENTITY) as AlarmEntity
                alarmCache.save(entity)
                return@fromCallable true
            }
        }
    }

    fun save(entity:AlarmEntity){
        val data = HashMap<String,AlarmEntity>()
        data[PARAM_MOVIE_ENTITY] = entity
        observable(data)
    }
}