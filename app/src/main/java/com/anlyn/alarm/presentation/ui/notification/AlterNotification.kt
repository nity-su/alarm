package com.anlyn.alarm.presentation.ui.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.concurrent.TimeUnit

class AlterNotification : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.getIntExtra("id",0)
        val mils = intent?.getLongExtra("mils",0)!!
        val hms = String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(mils) % TimeUnit.DAYS.toHours(1),
            TimeUnit.MILLISECONDS.toMinutes(mils) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(mils) % TimeUnit.MINUTES.toSeconds(1)
        )
        val helper = NotificationHelper(context)
        val bn = helper.getChannelNotification(hms)
        helper.getManager()!!.notify(id!!,bn!!.build())
        //music player
    }
}