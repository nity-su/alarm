package com.anlyn.alarm.presentation.ui.notification

import android.R
import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import com.anlyn.data.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.anlyn.alarm.presentation.ui.ringingalarm.RingingActivity
import java.io.Serializable
import java.net.URI


class NotificationHelper(val base: Context?) : ContextWrapper(base) {
    companion object {
        val channelID = "channelID"
        val channelName = "Channel Name"
    }
    private var mManager: NotificationManager? = null

    init{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel =
            NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
        getManager()!!.createNotificationChannel(channel)
    }

    fun getManager(): NotificationManager? {
        if (mManager == null) {
            mManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        }
        return mManager
    }

    fun getChannelNotification(id:Int,time:String,path:String): NotificationCompat.Builder? {
        val ringingIntent = Intent(base,RingingActivity::class.java).apply {
            this.putExtra("path",path)
            this.putExtra("id",id)
        }
        val fullScreenPendingIntent =PendingIntent.getActivity(this, 0,
            ringingIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        return NotificationCompat.Builder(
            applicationContext,
            channelID
        )
            .setContentTitle("Alarm!")
            .setContentText("Your AlarmManager is working.\n"+time)
            .setSmallIcon(R.drawable.ic_lock_idle_alarm)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true) //touch cancel
            .setOngoing(true)
            .setFullScreenIntent(fullScreenPendingIntent,true)

    }
}
//            .setContentIntent(PendingIntent.getActivity(base,1, intent,0))