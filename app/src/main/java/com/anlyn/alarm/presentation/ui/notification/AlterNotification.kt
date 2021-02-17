package com.anlyn.alarm.presentation.ui.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.anlyn.data.MediaPlayer
import java.util.concurrent.TimeUnit

class AlterNotification : BroadcastReceiver(){
    //notification 제거 음악 중지
    //notification click -> 받아쓰기 Activity 생성
    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.getIntExtra("id",0)
        val mils = intent?.getLongExtra("mils",0)!!
        val musicUriStr = intent?.getStringExtra("uri")
        val hms = String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(mils) % TimeUnit.DAYS.toHours(1),
            TimeUnit.MILLISECONDS.toMinutes(mils) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(mils) % TimeUnit.MINUTES.toSeconds(1)
        )

        val helper = NotificationHelper(context)
        val bn = helper.getChannelNotification(id!!,hms,musicUriStr)
        helper.getManager()!!.notify(id,bn!!.build())

        MediaPlayer.init(context!!,musicUriStr)
        MediaPlayer.prepare()
        MediaPlayer.start()

        //music player
    }
}