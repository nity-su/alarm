package com.anlyn.alarm.presentation.ui.ringingalarm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anlyn.domain.Repository.RemoteRepository
import com.anlyn.domain.usercase.PhraseLoadUseCase
import javax.inject.Inject

class RingingVMFactory @Inject constructor(private val useCase: PhraseLoadUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RingingViewModel(useCase) as T
    }
}