package com.anlyn.domain.usercase

import com.anlyn.domain.Repository.LocalRepository
import com.anlyn.domain.Transformer
import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable


class RemoveAlarmUseCase(private val localRepo: LocalRepository, transformer: Transformer<Boolean>) : UseCase<Boolean>(transformer){
    companion object {
        private const val PARAM_MOVIE_ENTITY = "movieEntity"
    }
    override fun createObservable(data: Map<String, Any?>?): Observable<Boolean> {
        val alarmEntity = data?.get(PARAM_MOVIE_ENTITY) as AlarmEntity
        alarmEntity.let {
            return Observable.fromCallable {
                localRepo.remove(it)
                return@fromCallable true
            }
        }
    }
    fun remove(data:Map<String,AlarmEntity>){
        observable(data)
    }
}