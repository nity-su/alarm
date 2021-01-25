package com.anlyn.alarmwheater.presentation.entities

data class Alarm(
    val id:Int,
    val time:Int,
    val mon:Boolean,
    val teu:Boolean,
    val wed:Boolean,
    val thu:Boolean,
    val fri:Boolean,
    val sat:Boolean,
    val sun:Boolean
){
}