package com.anlyn.alarm.presentation.ui.displayalarm

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anlyn.domain.models.AlarmEntity
import com.anlyn.domain.usercase.GetAllAlarmUseCase

class DisplayAlarmViewModel (val getAllAlarmUseCase: GetAllAlarmUseCase) : ViewModel(){
    private val _alarmCacheLiveData = MutableLiveData<List<AlarmEntity>>()
     var alarmCacheLiveData : LiveData<List<AlarmEntity>> = _alarmCacheLiveData
//https://android.jlelse.eu/how-to-bind-a-list-of-items-to-a-recyclerview-with-android-data-binding-1bd08b4796b4
        init{
    getAllAlarmEntity()
}
    fun getAllAlarmEntity(){
        getAllAlarmUseCase.observable().subscribe({values -> _alarmCacheLiveData.value = values
            alarmCacheLiveData = _alarmCacheLiveData})

//        alarmCacheLiveData.value =
    }
    fun dadsd(activity: AppCompatActivity){


    }
}