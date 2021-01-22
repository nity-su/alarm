package com.anlyn.data.db.local
import androidx.room.Database
import androidx.room.RoomDatabase
import com.anlyn.data.entities.AlarmData
import com.anlyn.data.db.local.AlarmRoomConstant.DB_VERSION
import javax.inject.Inject

@Database(entities = [AlarmData::class],version = DB_VERSION)
 abstract class AppDataBase : RoomDatabase(){
    abstract fun alarmDataDao(): AlarmDataDao
}