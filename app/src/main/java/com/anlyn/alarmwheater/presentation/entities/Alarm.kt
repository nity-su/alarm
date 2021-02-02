package com.anlyn.alarmwheater.presentation.entities

import com.anlyn.alarmwheater.R

data class Alarm(
    val id:Int,
    val time:Int,
    val mon:Boolean,
    val tue:Boolean,
    val wed:Boolean,
    val thu:Boolean,
    val fri:Boolean,
    val sat:Boolean,
    val sun:Boolean
){
    fun getDayOfWeekMap():HashMap<Int,Boolean>{
        val map = HashMap<Int,Boolean>()
        map[R.id.mon_btn] = mon
        map[R.id.tue_btn] = tue
        map[R.id.wed_btn] = wed
        map[R.id.thu_btn] = thu
        map[R.id.fri_btn] = fri
        map[R.id.sat_btn] = sat
        map[R.id.sun_btn] = sun
        return map
    }
}