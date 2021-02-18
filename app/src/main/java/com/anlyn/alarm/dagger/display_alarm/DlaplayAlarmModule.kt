package com.anlyn.alarm.dagger.display_alarm

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.anlyn.alarm.dagger.setting_alarm.SettingAlarmModule
import com.anlyn.alarm.presentation.common.ASyncTransformer
import com.anlyn.alarm.presentation.ui.alarmsetting.SettingActivity
import com.anlyn.alarm.presentation.ui.displayalarm.DisplayAlarmActivity
import com.anlyn.alarm.presentation.ui.displayalarm.DisplayAlarmVMFactory
import com.anlyn.alarm.presentation.ui.displayalarm.DisplayAlarmViewModel
import com.anlyn.alarm.presentation.ui.notification.AlterNotification
import com.anlyn.data.alarmmanger.AlarmMangerHelperImpl
import com.anlyn.data.db.local.RoomAlarmDataSource
import com.anlyn.domain.Repository.LocalRepository
import com.anlyn.domain.cache.AlarmMangerHelper
import com.anlyn.domain.usercase.GetAllAlarmUseCase
import com.anlyn.domain.usercase.RemoveAlarmUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named
//object가 되는 이유는 바디{}에 있는 함수가 모두 static으로 처리되기 때문
@Module
object DisplayAlarmModule {
    @Provides
    @JvmStatic
    @Named("getAllAlarmUseCase")
    fun providesGetAllAlarmUseCase(@Named("LocalRepo")  localRepo: LocalRepository): GetAllAlarmUseCase{
        return GetAllAlarmUseCase(ASyncTransformer(),localRepo)
    }
    @Provides
    @JvmStatic
    fun providesVMFactory(@Named("getAllAlarmUseCase") getAllAlarmUseCase: GetAllAlarmUseCase,
                          @Named("removeAlarmUseCase") removeAlarmUseCase: RemoveAlarmUseCase): DisplayAlarmVMFactory {
        return DisplayAlarmVMFactory(
            getAllAlarmUseCase,removeAlarmUseCase
        )
    }
//    @Provides
//    @JvmStatic
//    fun providesAlarmListViewModel(@Named("getAllAlarmUseCase") getAllAlarmUseCase: GetAllAlarmUseCase,
//                                   removeAlarmUseCase: RemoveAlarmUseCase) : ViewModel{
//        return DisplayAlarmViewModel(
//            getAllAlarmUseCase,removeAlarmUseCase
//        )
//    }

    @JvmStatic
    @Provides
    fun providesAlarmMangerHelper(activity: DisplayAlarmActivity):AlarmMangerHelper{
        val intent = Intent(activity.applicationContext, AlterNotification::class.java)
        return AlarmMangerHelperImpl(activity.applicationContext,intent)
    }

    @Provides
    @JvmStatic
    @Named("removeAlarmUseCase")
    fun providesRemoveUseCase( @Named("LocalRepo") localRepo: LocalRepository,
                               alarmMangerHelper: AlarmMangerHelper):RemoveAlarmUseCase{
        return RemoveAlarmUseCase(localRepo,alarmMangerHelper,ASyncTransformer())
    }

}