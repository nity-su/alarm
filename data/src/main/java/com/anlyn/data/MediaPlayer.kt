package com.anlyn.data

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import java.io.Serializable

object MediaPlayer{
    private var mediaPlayer: MediaPlayer? =null


    fun init(context: Context,uriString: String?){
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setAudioAttributes(AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM).build())
            mediaPlayer?.setDataSource(context, Uri.parse(uriString))
        }
    }

    fun prepare(){
        mediaPlayer?.prepare()
    }

    fun start(){
        mediaPlayer?.start()
    }

    fun stop(){
        mediaPlayer?.stop()
    }

}