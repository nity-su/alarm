package com.anlyn.domain.Repository

import com.anlyn.domain.models.QuestionEntity
import io.reactivex.rxjava3.core.Observable
import java.util.*

interface RemoteRepository {
    fun getSentence():Observable<QuestionEntity>
}