package com.anlyn.alarmwheater.Dagger.display_alarm

import androidx.lifecycle.ViewModel
import com.anlyn.alarmwheater.presentation.common.ASyncTransformer
import com.anlyn.alarmwheater.presentation.ui.displayalarm.DisplayAlarmVMFactory
import com.anlyn.alarmwheater.presentation.ui.displayalarm.DisplayAlarmViewModel
import com.anlyn.domain.AlarmCache
import com.anlyn.domain.usercase.GetAllAlarmUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DisplayAlarmModule {

    @Provides
    @AlarmActivityScope
    @Named("getAllAlarmUseCase")
    fun ProvidesGetAllAlarmUseCase(@Named("AlarmCache")  alarmCache: AlarmCache): GetAllAlarmUseCase{
        return GetAllAlarmUseCase(ASyncTransformer(),alarmCache)
    }

    @Provides
    @AlarmActivityScope
    fun ProvidesVMFactory(@Named("getAllAlarmUseCase") getAllAlarmUseCase: GetAllAlarmUseCase): DisplayAlarmVMFactory {
        return DisplayAlarmVMFactory(
            getAllAlarmUseCase
        )
    }
    @Provides
    @AlarmActivityScope
    fun ProvidesMovieListViewModel(@Named("getAllAlarmUseCase") getAllAlarmUseCase: GetAllAlarmUseCase) : ViewModel{
        return DisplayAlarmViewModel(
            getAllAlarmUseCase
        )
    }
}