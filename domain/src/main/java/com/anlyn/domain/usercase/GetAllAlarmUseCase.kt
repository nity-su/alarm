package com.anlyn.domain.usercase

import com.anlyn.domain.AlarmCache
import com.anlyn.domain.Transformer
import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable

class GetAllAlarmUseCase(transformer: Transformer<List<AlarmEntity>>,private val alarmCache: AlarmCache) : UseCase<List<AlarmEntity>>(transformer){

    companion object {
        private const val PARAM_MOVIE_ENTITY = "movieEntity"
    }

    override fun createObservable(data: Map<String, Any?>?): Observable<List<AlarmEntity>> {
        return alarmCache.getAll()
    }

}