package com.anlyn.domain.usercase

import com.anlyn.domain.AlarmCache
import com.anlyn.domain.Transformer
import io.reactivex.rxjava3.core.Observable

class ReviseAlarmUseCase(transformer: Transformer<Boolean>,alarmCache: AlarmCache) : UseCase<Boolean>(transformer){
    override fun createObservable(data: Map<String, Any?>?): Observable<Boolean> {
        TODO("Not yet implemented")
    }
}