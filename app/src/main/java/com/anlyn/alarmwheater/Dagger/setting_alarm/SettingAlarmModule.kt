package com.anlyn.alarmwheater.Dagger.setting_alarm

import com.anlyn.alarmwheater.presentation.common.ASyncTransformer
import com.anlyn.alarmwheater.presentation.ui.alarmsetting.SettingVMFactory
import com.anlyn.domain.AlarmCache
import com.anlyn.domain.usercase.AddAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object SettingAlarmModule {
    @JvmStatic
    @Provides
    fun providesAddAlarmUseCase(@Named("AlarmCache") alarmCache: AlarmCache):AddAlarmUseCase{
        return AddAlarmUseCase(ASyncTransformer(),alarmCache)
    }
    @JvmStatic
    @Provides
    fun providesSettingAlarmVMFactory(addAlarmUseCase: AddAlarmUseCase):SettingVMFactory{
        return SettingVMFactory(addAlarmUseCase)
    }

}