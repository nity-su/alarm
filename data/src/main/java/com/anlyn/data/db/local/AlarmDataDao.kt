package com.anlyn.data.db.local

import androidx.room.*
import com.anlyn.data.entities.AlarmData
import com.anlyn.domain.models.AlarmEntity

@Dao
interface AlarmDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlarmData(data:AlarmData)

    @Update
    fun updateAlarmData(data:AlarmData)

    @Delete
    fun deleteAlarmData(data:AlarmData)

    @Query("SELECT * FROM alarmSetCondition")
    fun getAllAlarmSetCondition():List<AlarmData>?

}