package com.anlyn.domain.models
//1:1 for AlarmData
class AlarmEntity(
    val id:Int = 0,
    val hour:Int = 0,
    val minute:Int =0,
    val mon:Boolean = false,
    val tue:Boolean = false,
    val wed:Boolean = false,
    val thu:Boolean = false,
    val fri:Boolean = false,
    val sat:Boolean = false,
    val sun:Boolean = false
){
}
