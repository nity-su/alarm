package com.anlyn.alarmwheater.presentation.ui.displayalarm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anlyn.alarmwheater.AlarmAppApplication
import com.anlyn.alarmwheater.databinding.ActivityDisplayAlarmBinding
import com.anlyn.alarmwheater.presentation.navigation.Navigator
import com.anlyn.alarmwheater.presentation.ui.alarmsetting.SettingActivity
import com.anlyn.domain.AlarmCache
import com.anlyn.domain.models.AlarmEntity
import dagger.android.AndroidInjection
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Named

class DisplayAlarmActivity : AppCompatActivity() {
    private val TAG = this::class.simpleName
    private val REQUEST_CODE =1011

    @Inject
    lateinit var factory : DisplayAlarmVMFactory
    @Inject @field:Named("AlarmCache")
    lateinit var cache: AlarmCache
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
//        val model : DisplayAlarmViewModel by viewModels() { factory }
//        val adapter =
//            DisplayAlarmAdapter(
//                model.alarmCacheLiveData.value
//            )
//        Log.d(TAG,"size:"+model.alarmCacheLiveData.value?.size.toString())
//        binding.recylerView.layoutManager = LinearLayoutManager(applicationContext)
//        binding.recylerView.adapter = adapter
//        model.alarmCacheLiveData.observe(this, Observer {
//            adapter.setList(it)
//        })

//        binding.openSettingBtn.setOnClickListener({navigator.navigatorAlarmSetting(this,null,REQUEST_CODE)})
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
