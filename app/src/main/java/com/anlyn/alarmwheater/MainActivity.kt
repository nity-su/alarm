package com.anlyn.alarmwheater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.anlyn.alarmwheater.Dagger.DaggerMainComponent
import com.anlyn.alarmwheater.Dagger.MainComponent
import com.anlyn.data.db.local.AppDataBase
import com.anlyn.data.db.local.RoomAlarmCache
import com.anlyn.data.entities.AlarmData
import com.anlyn.domain.AlarmCache
import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.simpleName

    @Inject
    lateinit var cache: AlarmCache
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
