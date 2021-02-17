package com.anlyn.data.db

import com.anlyn.data.db.remote.PhraseDataSource
import com.anlyn.domain.Repository.RemoteRepository
import com.anlyn.domain.models.QuestionEntity
import io.reactivex.rxjava3.core.Observable

import retrofit2.Retrofit

class RemoteRepositoryImpl(val source: PhraseDataSource):RemoteRepository{
    override fun getSentence(): Observable<QuestionEntity> {
        return source.getSentenceObservable()
    }
}