package com.anlyn.alarm.presentation.ui.alarmsetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anlyn.alarm.databinding.ActivitySettingBinding
import com.anlyn.domain.usercase.AddAlarmUseCase

class SettingVMFactory constructor(private val addAlarmUseCase: AddAlarmUseCase,
                                   private val binding:ActivitySettingBinding) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingViewModel(addAlarmUseCase,binding) as T
    }
}