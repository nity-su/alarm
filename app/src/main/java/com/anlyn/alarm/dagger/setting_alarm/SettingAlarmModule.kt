package com.anlyn.alarm.dagger.setting_alarm

import android.content.Context
import android.content.Intent
import android.util.Log
import com.anlyn.alarm.databinding.ActivitySettingBinding
import com.anlyn.alarm.presentation.common.ASyncTransformer
import com.anlyn.alarm.presentation.ui.alarmsetting.SettingActivity
import com.anlyn.alarm.presentation.ui.alarmsetting.SettingVMFactory
import com.anlyn.alarm.presentation.ui.notification.AlterNotification
import com.anlyn.data.alarmmanger.AlarmMangerHelperImpl
import com.anlyn.data.db.local.RoomAlarmDataSource
import com.anlyn.domain.Repository.LocalRepository
import com.anlyn.domain.cache.AlarmMangerHelper
import com.anlyn.domain.usercase.AddAlarmUseCase
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object SettingAlarmModule {
    @JvmStatic
    @Provides
    fun providesAddAlarmUseCase(@Named("LocalRepo") localRepo: LocalRepository,
                                alarmMangerHelper: AlarmMangerHelper):AddAlarmUseCase{
        return AddAlarmUseCase(ASyncTransformer(),localRepo,alarmMangerHelper)
    }

    @JvmStatic
    @SettingActivityScope
    @Provides
    fun providesActivitySettingAlarmBinding(activity: SettingActivity):ActivitySettingBinding{
        return ActivitySettingBinding.inflate(activity.layoutInflater)
    }

    @JvmStatic
    @Provides
    fun providesSettingAlarmVMFactory(addAlarmUseCase: AddAlarmUseCase,
                                      binding:ActivitySettingBinding):SettingVMFactory{
        return SettingVMFactory(addAlarmUseCase,binding)
    }

    @JvmStatic
    @Provides
    fun providesAlarmMangerHelper(activity: SettingActivity):AlarmMangerHelper{
        val intent = Intent(activity.applicationContext, AlterNotification::class.java)
        return AlarmMangerHelperImpl(activity.applicationContext,intent)
    }


}