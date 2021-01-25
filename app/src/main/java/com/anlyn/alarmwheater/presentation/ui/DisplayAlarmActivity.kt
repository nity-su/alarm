package com.anlyn.alarmwheater.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anlyn.alarmwheater.R
import com.anlyn.alarmwheater.databinding.ActivityDisplayAlarmBinding
import com.anlyn.domain.AlarmCache
import java.util.zip.Inflater
import javax.inject.Inject

class DisplayAlarmActivity : AppCompatActivity() {
    private val TAG = this::class.simpleName
    @Inject
    lateinit var factory : DisplayAlarmVMFactory
    lateinit var binding : ActivityDisplayAlarmBinding
    lateinit var viewModel :DisplayAlarmViewModel
    @Inject
    lateinit var cache: AlarmCache
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(applicationContext)
        binding = ActivityDisplayAlarmBinding.inflate(inflater)
        setContentView(binding.root)

    }

    fun initUI(){
        val model : DisplayAlarmViewModel by viewModels() { factory }

        val adapter : DisplayAlarmAdapter = DisplayAlarmAdapter(model.alarmCacheLiveData.value)
        binding.recylerView.adapter = adapter
    }
}
