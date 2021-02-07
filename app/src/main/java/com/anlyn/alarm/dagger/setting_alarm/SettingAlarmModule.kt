package com.anlyn.alarm.dagger.setting_alarm

import android.content.Context
import android.content.Intent
import android.util.Log
import com.anlyn.alarm.presentation.common.ASyncTransformer
import com.anlyn.alarm.presentation.ui.alarmsetting.SettingActivity
import com.anlyn.alarm.presentation.ui.alarmsetting.SettingVMFactory
import com.anlyn.alarm.presentation.ui.notification.AlterNotification
import com.anlyn.data.alarmmanger.AlarmMangerHelperImpl
import com.anlyn.domain.cache.AlarmCache
import com.anlyn.domain.cache.AlarmMangerHelper
import com.anlyn.domain.usercase.AddAlarmUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object SettingAlarmModule {
    @JvmStatic
    @Provides
    fun providesAddAlarmUseCase(@Named("AlarmCache") alarmCache: AlarmCache,alarmMangerHelper: AlarmMangerHelper):AddAlarmUseCase{
        return AddAlarmUseCase(ASyncTransformer(),alarmCache,alarmMangerHelper)
    }
    @JvmStatic
    @Provides
    fun providesSettingAlarmVMFactory(addAlarmUseCase: AddAlarmUseCase):SettingVMFactory{
        return SettingVMFactory(addAlarmUseCase)
    }
    @JvmStatic
    @Provides
    @Named("SettingActivity")
    fun providesAlarmMangerIntent(activity: SettingActivity):Intent{
        Log.d(SettingAlarmModule::class.simpleName,"exe")
        return Intent(activity.applicationContext, AlterNotification::class.java)
    }
    @JvmStatic
    @Provides
    fun providesAlarmMangerHelper(activity: SettingActivity,@Named("SettingActivity") intent: Intent):AlarmMangerHelper{
        return AlarmMangerHelperImpl(activity.applicationContext,intent)
    }

}