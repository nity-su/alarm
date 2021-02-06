package com.anlyn.alarm.presentation.ui.displayalarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anlyn.domain.usercase.GetAllAlarmUseCase
import javax.inject.Inject

class DisplayAlarmVMFactory @Inject constructor (val getAllAlarmUseCase: GetAllAlarmUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DisplayAlarmViewModel(
            getAllAlarmUseCase
        ) as T
    }
}