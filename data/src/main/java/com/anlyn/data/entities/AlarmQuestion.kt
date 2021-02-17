package com.anlyn.data.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AlarmQuestion {
    @SerializedName("sentence")
    @Expose
     var sentence:String? =null
}