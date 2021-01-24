package com.anlyn.alarmwheater.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.anlyn.alarmwheater.R
import com.anlyn.alarmwheater.databinding.ActivityDisplayAlarmBinding
import com.anlyn.domain.AlarmCache
import java.util.zip.Inflater
import javax.inject.Inject

class DisplayAlarmActivity : AppCompatActivity() {
    private val TAG = this::class.simpleName
    lateinit var binding : ActivityDisplayAlarmBinding
    @Inject
    lateinit var cache: AlarmCache
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(applicationContext)
        binding = ActivityDisplayAlarmBinding.inflate(inflater)
        setContentView(binding.root)
    }

    fun initUI(){

    }
}
