package com.anlyn.data.db.remote

import com.anlyn.data.entities.AlarmQuestion
import com.anlyn.data.mapper.AlarmQuestionEntityMapper
import com.anlyn.domain.Mapper
import com.anlyn.domain.models.AlarmEntity
import com.anlyn.domain.models.QuestionEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

import retrofit2.Retrofit
import java.util.concurrent.Callable

class PhraseDataSource(val mapper: Mapper<AlarmQuestion,QuestionEntity>) {
    val TAG = PhraseDataSource::class.simpleName
    val api:PhraseApi
    init{
        api = RetrofitClient.create()
    }

    fun getSentenceObservable(): Observable<QuestionEntity> {
         return api.getAlarmQuestion()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .flatMap({
            q -> Observable.fromCallable( object : Callable<QuestionEntity>{
             override fun call(): QuestionEntity {
                return mapper.mapFrom(q)
             }
         })
         })
    }
}