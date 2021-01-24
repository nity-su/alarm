package com.anlyn.alarmwheater.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anlyn.domain.models.AlarmEntity

class DisplayAlarmViewModel : ViewModel(){
    lateinit var alarmCacheLiveData : LiveData<List<AlarmEntity>>


    fun getAlarmEntity(){
//        val useCase = AddA()
    }
}