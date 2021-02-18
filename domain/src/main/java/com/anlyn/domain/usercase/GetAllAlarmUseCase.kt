package com.anlyn.domain.usercase

import com.anlyn.domain.Repository.LocalRepository
import com.anlyn.domain.Transformer
import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable

class GetAllAlarmUseCase(transformer: Transformer<List<AlarmEntity>>,private val localRepo: LocalRepository) : UseCase<List<AlarmEntity>>(transformer){

    companion object {
//
    }

    override fun createObservable(data: Map<String, Any?>?): Observable<List<AlarmEntity>> {
        return localRepo.getAll()
    }

}