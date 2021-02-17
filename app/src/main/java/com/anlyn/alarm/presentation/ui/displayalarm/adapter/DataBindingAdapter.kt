package com.anlyn.alarm.presentation.ui.displayalarm.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.lang.StringBuilder

@BindingAdapter(value = ["hour","minute"],requireAll = false)
fun displayTime(view: TextView, hour:Int, minute:Int){
    val time = StringBuilder()
        .append(hour)
        .append(" : ")
        .append(minute)
    view.setText(time.toString())
}