package com.anlyn.alarm.dagger.data

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.room.Room
import com.anlyn.alarm.dagger.setting_alarm.SettingAlarmModule
import com.anlyn.alarm.presentation.ui.alarmsetting.SettingActivity
import com.anlyn.alarm.presentation.ui.notification.AlterNotification
import com.anlyn.data.db.LocalRepositoryImpl
import com.anlyn.data.db.RemoteRepositoryImpl
import com.anlyn.data.db.local.AppDataBase
import com.anlyn.data.db.local.RoomAlarmDataSource
import com.anlyn.data.db.remote.PhraseDataSource
import com.anlyn.data.entities.AlarmQuestion
import com.anlyn.data.mapper.AlarmDataEntityMapper
import com.anlyn.data.mapper.AlarmEntityDataMapper
import com.anlyn.data.mapper.AlarmQuestionEntityMapper
import com.anlyn.domain.Mapper
import com.anlyn.domain.Repository.LocalRepository
import com.anlyn.domain.Repository.RemoteRepository
import com.anlyn.domain.models.QuestionEntity
import dagger.Module
import dagger.Provides
import javax.inject.Named

//https://stackoverflow.com/questions/44894218/dagger-2-static-provider-methods-in-kotlin
@Module
object DataModule{

        @JvmStatic
        @Provides
        fun providesRoomDataBase(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "AlarmDB"
            ).build()
        }
        @JvmStatic
        @Provides
        @Named("RoomAlarmDataSource")
        fun providesRoomAlarmDataSource(
            database: AppDataBase,
            alarmDataEntityMapper: AlarmDataEntityMapper,
            alarmEntityDataMapper: AlarmEntityDataMapper
        ): RoomAlarmDataSource {
            return RoomAlarmDataSource(database, alarmEntityDataMapper, alarmDataEntityMapper)
        }

        @JvmStatic
        @Provides
        @Named("LocalRepo")
        fun providesLocalRepo(@Named("RoomAlarmDataSource") dataSource: RoomAlarmDataSource):LocalRepository{
            return LocalRepositoryImpl(dataSource)
        }

        @JvmStatic
        @Provides
        fun providesPhraseDataSource(mapper: AlarmQuestionEntityMapper):PhraseDataSource
                = PhraseDataSource(mapper)

        @JvmStatic
        @Provides
        @Named("RemoteRepoImpl")
        fun providesRemoteRepository(source: PhraseDataSource): RemoteRepository = RemoteRepositoryImpl(source)


}