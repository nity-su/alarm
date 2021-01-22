package com.anlyn.domain.models

class AlarmEntity(
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