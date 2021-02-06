package com.anlyn.data.alarmmanger

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.anlyn.domain.cache.AlarmMangerHelper
import com.anlyn.domain.models.AlarmEntity
import java.util.*
import javax.inject.Inject
import kotlin.math.min


class AlarmMangerHelperImpl @Inject constructor(val context: Context, val intent:Intent): AlarmMangerHelper {//
    private var calendar = Calendar.getInstance()
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
    private var hour:Int =0
    private var minute:Int =0;
    override fun startAlarm(alarmEntity: AlarmEntity) {
        hour = alarmEntity.hour
        minute = alarmEntity.minute
        alarmSetDayOfWeek(alarmEntity,this::add)
    }
    override fun cancelAlarm(alarmEntity: AlarmEntity) {
        hour = alarmEntity.hour
        minute = alarmEntity.minute
        alarmSetDayOfWeek(alarmEntity,this::cancel)
    }

    fun alarmSetDayOfWeek(alarmEntity: AlarmEntity,setting:(Int)->Unit){
        if(alarmEntity.sun){
            setting(Calendar.SUNDAY)
        }
        if(alarmEntity.mon){
            setting(Calendar.MONDAY)
        }
        if(alarmEntity.tue){
            setting(Calendar.TUESDAY)
        }
        if(alarmEntity.wed){
            setting(Calendar.WEDNESDAY)
        }
        if(alarmEntity.thu){
            setting(Calendar.THURSDAY)
        }
        if(alarmEntity.fri){
            setting(Calendar.FRIDAY)
        }
        if(alarmEntity.sat){
            setting(Calendar.SATURDAY)
        }
    }

    private fun add(chk_week:Int){
        calendar.set(Calendar.HOUR,chk_week)
        calendar.set(Calendar.MINUTE,hour)
        calendar.set(Calendar.DAY_OF_WEEK,minute)
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        val requestCode = getRequestCode(chk_week)

        intent.putExtra("time",requestCode)
        val pendingIntent = PendingIntent.getBroadcast(context,requestCode ,
            intent, 0) //this
//        if (calendar.before(Calendar.getInstance())) {1
//            calendar.add(Calendar.DATE, 1)
//        }
//        alarmManager!!.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent)
        alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
            1*60*60*1000, pendingIntent)
    }

    private fun cancel(chk_week:Int){
        val pendingIntent = PendingIntent.getBroadcast(context, getRequestCode(chk_week), intent, 0) //this
        alarmManager!!.cancel(pendingIntent);
    }

    private fun getRequestCode(chk_week:Int):Int = (chk_week*60*60*24)+(hour*60)+minute
}