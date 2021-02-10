package com.anlyn.alarm.dagger.display_alarm

import androidx.lifecycle.ViewModel
import com.anlyn.alarm.presentation.common.ASyncTransformer
import com.anlyn.alarm.presentation.ui.displayalarm.DisplayAlarmVMFactory
import com.anlyn.alarm.presentation.ui.displayalarm.DisplayAlarmViewModel
import com.anlyn.data.db.local.RoomAlarmDataSource
import com.anlyn.domain.Repository.LocalRepository
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
    fun ProvidesGetAllAlarmUseCase(@Named("LocalRepo")  localRepo: LocalRepository): GetAllAlarmUseCase{
        return GetAllAlarmUseCase(ASyncTransformer(),localRepo)
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
    fun ProvidesAlarmListViewModel(@Named("getAllAlarmUseCase") getAllAlarmUseCase: GetAllAlarmUseCase) : ViewModel{
        return DisplayAlarmViewModel(
            getAllAlarmUseCase
        )
    }
}