package com.anlyn.data.db.remote

import com.anlyn.data.entities.AlarmQuestion
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface PhraseApi {
    @Headers(
        "Content-Type: application/json"
    )
    @GET("contents/phrase.php")
    fun getAlarmQuestion(): Observable<AlarmQuestion>
}