package com.anlyn.data.entities
//Sun, Mon, Tue, Wed, Thu, Fri, Sat - infos
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.net.URI

@Entity (tableName = "alarmSetCondition")
data class AlarmData(
    @PrimaryKey (autoGenerate = true)
    val id:Int,
    val hour:Int,
    val minute:Int,
    @SerializedName("music_uri")
    val musicUriStr:String,
    val mon:Boolean,
    val teu:Boolean,
    val wed:Boolean,
    val thu:Boolean,
    val fri:Boolean,
    val sat:Boolean,
    val sun:Boolean
//받아쓰기 속성 리펙토링 필요
){
}