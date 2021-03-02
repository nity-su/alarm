package com.anlyn.alarm.presentation.ui.notification

import android.app.KeyguardManager
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService


class DeleteNotification : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

//        val km =
//            context?.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager?
//        val locked = km!!.isKeyguardLocked
//        if(intent?.action.equals("delete_action")&&locked){
//            val id = intent?.getIntExtra("id",0)
//            val mManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
//            mManager!!.cancel(id!!)
//        }
    }
}