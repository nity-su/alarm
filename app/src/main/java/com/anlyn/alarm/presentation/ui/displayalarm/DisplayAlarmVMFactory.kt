package com.anlyn.alarm.presentation.ui.displayalarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anlyn.domain.usercase.GetAllAlarmUseCase
import com.anlyn.domain.usercase.RemoveAlarmUseCase
import javax.inject.Inject

class DisplayAlarmVMFactory @Inject constructor (private val getAllAlarmUseCase: GetAllAlarmUseCase,
                                                 private val removeAlarmUseCase: RemoveAlarmUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DisplayAlarmViewModel(
            getAllAlarmUseCase,removeAlarmUseCase
        ) as T
    }
}