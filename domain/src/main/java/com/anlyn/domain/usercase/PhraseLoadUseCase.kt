package com.anlyn.domain.usercase

import com.anlyn.domain.Repository.RemoteRepository
import com.anlyn.domain.Transformer
import com.anlyn.domain.models.QuestionEntity
import io.reactivex.rxjava3.core.Observable

class PhraseLoadUseCase(private val remoteRepo: RemoteRepository,private val transformer: Transformer<QuestionEntity>) : UseCase<QuestionEntity>(transformer){
    override fun createObservable(data: Map<String, Any?>?): Observable<QuestionEntity> {
        return remoteRepo.getSentence()
    }
}