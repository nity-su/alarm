package com.anlyn.alarm.presentation.ui.ringingalarm

import android.app.NotificationManager
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anlyn.data.MediaPlayer
import com.anlyn.domain.Repository.RemoteRepository
import com.anlyn.domain.models.QuestionEntity
import com.anlyn.domain.usercase.PhraseLoadUseCase
import io.reactivex.rxjava3.core.Observable

class RingingViewModel(private val useCase: PhraseLoadUseCase) : ViewModel(){

    fun getPhrase():Observable<QuestionEntity>{
        return useCase.createObservable(null)
    }

    fun cancelClicked(){
        MediaPlayer.stop()
    }
}