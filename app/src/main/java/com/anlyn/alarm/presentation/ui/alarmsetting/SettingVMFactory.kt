package com.anlyn.alarm.presentation.ui.alarmsetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anlyn.domain.usercase.AddAlarmUseCase

class SettingVMFactory constructor(val addAlarmUseCase: AddAlarmUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingViewModel(addAlarmUseCase) as T
    }
}