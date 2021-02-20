package com.anlyn.alarm.presentation.ui.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.anlyn.data.MediaPlayer
import java.util.concurrent.TimeUnit

class AlterNotification : BroadcastReceiver(){
    val TAG = AlterNotification::class.simpleName
    //notification click -> 받아쓰기 Activity 생성
    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.getIntExtra("id",0)
        val musicUriStr = intent?.getStringExtra("uri")
        Thread(Runnable {
            MediaPlayer.init(context!!.applicationContext,musicUriStr)
            MediaPlayer.prepare()
            MediaPlayer.start()

        }).start()
        val helper = NotificationHelper(context)
        val bn = helper.getChannelNotification(id!!,musicUriStr)
        helper.getManager()!!.notify(id,bn!!.build())

        Log.d(TAG,"success receiver")
        //music player
    }
}