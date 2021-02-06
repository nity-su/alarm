package com.anlyn.alarm.presentation.ui.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlterNotification : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
       val helper = NotificationHelper(context)
        val bn = helper.getChannelNotification()
        helper.getManager()!!.notify(1,bn!!.build())

    }
}