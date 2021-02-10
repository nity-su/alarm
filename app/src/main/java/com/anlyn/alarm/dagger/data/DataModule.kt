package com.anlyn.alarm.dagger.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.anlyn.data.db.LocalRepositoryImpl
import com.anlyn.data.db.local.AppDataBase
import com.anlyn.data.db.local.RoomAlarmDataSource
import com.anlyn.data.mapper.AlarmDataEntityMapper
import com.anlyn.data.mapper.AlarmEntityDataMapper
import com.anlyn.domain.Repository.LocalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

//https://stackoverflow.com/questions/44894218/dagger-2-static-provider-methods-in-kotlin
@Module
 abstract class DataModule{
    @Module
    companion object{
        @JvmStatic
        @Provides
        fun providesRoomDataBase(context: Context): AppDataBase {
            Log.d("ProvidesRoomDataBase", "exe")
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "myDB"
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
            Log.d("RoomAlarmDataSource", "exe")
            return RoomAlarmDataSource(database, alarmEntityDataMapper, alarmDataEntityMapper)
        }

        @JvmStatic
        @Provides
        @Named("LocalRepo")
        fun providesLocalRepo(@Named("RoomAlarmDataSource") dataSource: RoomAlarmDataSource):LocalRepository{
            return LocalRepositoryImpl(dataSource)
        }
    }

}