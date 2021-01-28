package com.anlyn.alarmwheater.presentation.ui.displayalarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anlyn.alarmwheater.AlarmAppApplication
import com.anlyn.alarmwheater.databinding.ActivityDisplayAlarmBinding
import com.anlyn.domain.AlarmCache
import javax.inject.Inject
import javax.inject.Named

class DisplayAlarmActivity : AppCompatActivity() {
    private val TAG = this::class.simpleName
    @Inject
    lateinit var factory : DisplayAlarmVMFactory
    @Inject @field:Named("AlarmCache")
    lateinit var cache: AlarmCache

    lateinit var binding : ActivityDisplayAlarmBinding
    lateinit var viewModel : DisplayAlarmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = layoutInflater
        binding = ActivityDisplayAlarmBinding.inflate(inflater)
        setContentView(binding.root)

        val subComponent = (application as AlarmAppApplication).createDisplayAlarmSubComponent()
        subComponent.inject(this)

        initUI()
    }

    fun initUI(){

        val model : DisplayAlarmViewModel by viewModels() { factory }

        val adapter =
            DisplayAlarmAdapter(
                model.alarmCacheLiveData.value
            )
        Log.d(TAG,"size:"+model.alarmCacheLiveData.value?.size.toString())
        binding.recylerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recylerView.adapter = adapter
        model.alarmCacheLiveData.observe(this, Observer {
            adapter.setList(it)
        })


    }
}
