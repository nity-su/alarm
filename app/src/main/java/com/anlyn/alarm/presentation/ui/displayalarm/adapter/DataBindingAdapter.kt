package com.anlyn.alarm.presentation.ui.displayalarm.adapter

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anlyn.alarm.presentation.ui.displayalarm.DisplayAlarmViewModel
import com.anlyn.domain.models.AlarmEntity
import java.lang.StringBuilder
import java.util.function.Function

    @BindingAdapter(value = ["hour", "minute"], requireAll = false)
    fun displayTime(view: TextView, hour: Int, minute: Int) {
        val time = StringBuilder()
            .append(hour)
            .append(" : ")
            .append(minute)
        view.setText(time.toString())
    }

    @BindingAdapter(value = ["listener", "alarm_entity","live_data"], requireAll = false)
    fun alarmDelete(
        view: View,
        delete:DisplayAlarmViewModel.deleteEvent,
        alarmEntity: AlarmEntity?,
        liveData: LiveData<List<AlarmEntity>>
    ) {
        view.setOnClickListener({delete.event(alarmEntity!!).subscribe({
            liveData.value.let {
                if(it!!.contains(alarmEntity))
                    (it as ArrayList).remove(alarmEntity)
            (liveData as MutableLiveData).value = it
            }
        },{
            Log.d("error",it.message)
        })})


    }

