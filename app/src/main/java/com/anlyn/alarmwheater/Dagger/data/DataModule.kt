package com.anlyn.alarmwheater.Dagger.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.anlyn.data.db.local.AppDataBase
import com.anlyn.data.db.local.RoomAlarmCache
import com.anlyn.data.mapper.AlarmDataEntityMapper
import com.anlyn.data.mapper.AlarmEntityDataMapper
import com.anlyn.domain.AlarmCache
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
 class DataModule{

    @Singleton
        @Provides
        fun providesRoomDataBase(context: Context): AppDataBase {
            Log.d("ProvidesRoomDataBase", "exe")
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "myDB"
            ).build()
        }


        @Provides
        fun providesRoomAlarmCache(
            database: AppDataBase,
            alarmDataEntityMapper: AlarmDataEntityMapper,
            alarmEntityDataMapper: AlarmEntityDataMapper
        ): AlarmCache {
            Log.d("RoomAlarmCache", "exe")
            return RoomAlarmCache(database, alarmEntityDataMapper, alarmDataEntityMapper)
        }

}