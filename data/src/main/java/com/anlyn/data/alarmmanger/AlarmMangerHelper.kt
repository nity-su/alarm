package com.anlyn.data.alarmmanger

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.anlyn.domain.cache.AlarmMangerHelper
import com.anlyn.domain.models.AlarmEntity
import java.net.URI
import java.time.*
import java.time.temporal.TemporalAdjusters
import java.util.*
import javax.inject.Inject

//https://proandroiddev.com/android-alarmmanager-as-deep-as-possible-909bd5b64792
// AlarmManager will not be called with locked screen and enabled energy saving mode
class AlarmMangerHelperImpl constructor(val context: Context, val intent:Intent?): AlarmMangerHelper {//

    val TAG = AlarmMangerHelperImpl::class.simpleName
    private var calendar = Calendar.getInstance()
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
    private var hour:Int =0
    private var minute:Int =0;
    private lateinit var musicUriStr:String

    override fun startAlarm(alarmEntity: AlarmEntity) {
        hour = alarmEntity.hour
        minute = alarmEntity.minute
        musicUriStr = alarmEntity.musicUriStr!!
        alarmSetDayOfWeek(alarmEntity,this::add)
    }
    override fun cancelAlarm(alarmEntity: AlarmEntity) {
        hour = alarmEntity.hour
        minute = alarmEntity.minute
        musicUriStr = alarmEntity.musicUriStr!!
        alarmSetDayOfWeek(alarmEntity,this::cancel)
    }

    fun alarmSetDayOfWeek(alarmEntity: AlarmEntity,setting:(Int)->Unit){
        if(alarmEntity.sun){
            setting(DayOfWeek.SUNDAY.value)
        }
        if(alarmEntity.mon){
            setting(DayOfWeek.MONDAY.value)
        }
        if(alarmEntity.tue){
            setting(DayOfWeek.TUESDAY.value)
        }
        if(alarmEntity.wed){
            setting(DayOfWeek.WEDNESDAY.value)
        }
        if(alarmEntity.thu){
            setting(DayOfWeek.THURSDAY.value)
        }
        if(alarmEntity.fri){
            setting(DayOfWeek.FRIDAY.value)
        }
        if(alarmEntity.sat){
            setting(DayOfWeek.SATURDAY.value)
        }
    }

    private fun add(chk_week:Int){
//        val z: ZoneId = ZoneId.of("Asia/Seoul")
        var ld: LocalDate = LocalDate.now()
        val nowDow: DayOfWeek = LocalDate.now().dayOfWeek
        val lt: LocalTime = LocalTime.of(hour, minute)
        val nowZdt: ZonedDateTime = ZonedDateTime.now()
        val nowMils:Long = nowZdt.toInstant().toEpochMilli()
        val zdt: LocalDateTime = LocalDateTime.of(ld, lt)
        var targetZdtMils = zdt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        if(nowDow.value<chk_week)
            ld = ld.with(TemporalAdjusters.next(DayOfWeek.of(chk_week)))

        if(nowMils>targetZdtMils){
            ld = ld.with(TemporalAdjusters.next(DayOfWeek.of(chk_week)))
            targetZdtMils = LocalDateTime.of(ld, lt).atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli()
            Log.d(TAG,"next dayOfWeek")
        }

        val requestCode = getRequestCode(chk_week)

        intent!!.putExtra("id",requestCode)
            .putExtra("uri",musicUriStr)
        val pendingIntent = PendingIntent.getBroadcast(context,requestCode ,
            intent,PendingIntent.FLAG_UPDATE_CURRENT) //this
        alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP, targetZdtMils,
            7*24*60*60*1000, pendingIntent)
    }

    private fun cancel(chk_week:Int){
        val requestCode = getRequestCode(chk_week)
        Log.d("minute",minute.toString())
        intent!!.putExtra("id",requestCode)
            .putExtra("uri",musicUriStr)
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode,
            intent, PendingIntent.FLAG_UPDATE_CURRENT) //this
        alarmManager!!.cancel(pendingIntent);
    }

    private fun getRequestCode(chk_week:Int):Int = (chk_week*60*60*24)+(hour*60)+minute
}