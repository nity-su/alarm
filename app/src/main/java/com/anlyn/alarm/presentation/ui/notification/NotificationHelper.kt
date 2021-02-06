package com.anlyn.alarm.presentation.ui.notification

import android.R
import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.core.app.NotificationCompat


class NotificationHelper(base: Context?) : ContextWrapper(base) {
    companion object {
        val channelID = "channelID"
        val channelName = "Channel Name"
    }
    private var mManager: NotificationManager? = null

    fun NotificationHelper(base: Context?) {
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

    fun getChannelNotification(): NotificationCompat.Builder? {
        return NotificationCompat.Builder(
            applicationContext,
            channelID
        )
            .setContentTitle("Alarm!")
            .setContentText("Your AlarmManager is working.")
    }
}