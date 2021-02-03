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
//object가 되는 이유는 바디{}에 있는 함수가 모두 static으로 처리되기 때문
@Module
object DisplayAlarmModule {

    @Provides
    @JvmStatic
    @Named("getAllAlarmUseCase")
    fun ProvidesGetAllAlarmUseCase(@Named("AlarmCache")  alarmCache: AlarmCache): GetAllAlarmUseCase{
        return GetAllAlarmUseCase(ASyncTransformer(),alarmCache)
    }

    @Provides
    @JvmStatic
    fun ProvidesVMFactory(@Named("getAllAlarmUseCase") getAllAlarmUseCase: GetAllAlarmUseCase): DisplayAlarmVMFactory {
        return DisplayAlarmVMFactory(
            getAllAlarmUseCase
        )
    }
    @Provides
    @JvmStatic
    fun ProvidesMovieListViewModel(@Named("getAllAlarmUseCase") getAllAlarmUseCase: GetAllAlarmUseCase) : ViewModel{
        return DisplayAlarmViewModel(
            getAllAlarmUseCase
        )
    }
}