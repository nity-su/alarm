package com.anlyn.alarmwheater.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anlyn.domain.usercase.GetAllAlarmUseCase

class DisplayAlarmVMFactory(val getAllAlarmUseCase: GetAllAlarmUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DisplayAlarmViewModel(getAllAlarmUseCase) as T
    }
}