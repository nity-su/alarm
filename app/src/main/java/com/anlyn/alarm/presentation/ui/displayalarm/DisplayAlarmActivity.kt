package com.anlyn.alarm.presentation.ui.displayalarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anlyn.alarm.databinding.ActivityDisplayAlarmBinding
import com.anlyn.alarm.presentation.navigation.Navigator
import dagger.android.AndroidInjection
import javax.inject.Inject
import javax.inject.Named

class DisplayAlarmActivity : AppCompatActivity() {
    private val TAG = this::class.simpleName
    private val REQUEST_CODE =1011

    @Inject
    lateinit var factory : DisplayAlarmVMFactory
//    @Inject @field:Named("AlarmCache")

    @Inject
    lateinit var navigator: Navigator
    lateinit var binding : ActivityDisplayAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val inflater = layoutInflater
        binding = ActivityDisplayAlarmBinding.inflate(inflater)
        setContentView(binding.root)

//        val subComponent = (application as AlarmAppApplication).createDisplayAlarmSubComponent()
//        subComponent.inject(this)

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

        binding.openSettingBtn.setOnClickListener({navigator.navigatorAlarmSetting(this,null,REQUEST_CODE)})
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
