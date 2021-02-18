package com.anlyn.domain.models

//use this in usecase
//1:1 for AlarmData
data class AlarmEntity(
    var id:Int = 0,
    var hour:Int = 0,
    var minute:Int =0,
    var musicUriStr:String="",
    var mon:Boolean = false,
    var tue:Boolean = false,
    var wed:Boolean = false,
    var thu:Boolean = false,
    var fri:Boolean = false,
    var sat:Boolean = false,
    var sun:Boolean = false
){
}