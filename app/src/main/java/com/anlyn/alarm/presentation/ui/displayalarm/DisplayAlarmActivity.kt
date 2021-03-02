package com.anlyn.alarm.presentation.ui.displayalarm

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anlyn.alarm.databinding.ActivityDisplayAlarmBinding
import com.anlyn.alarm.presentation.navigation.Navigator
import com.anlyn.alarm.presentation.ui.displayalarm.adapter.DisplayAlarmAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject

class DisplayAlarmActivity : AppCompatActivity() {
    private val TAG = this::class.simpleName
    private val REQUEST_CODE =1011
    private val RECORD_REQUEST_CODE = 1
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
        setupPermissions()
        initUI()
    }

    fun initUI(){
        val model : DisplayAlarmViewModel by viewModels() { factory }
        val adapter =
            DisplayAlarmAdapter(model.alarmLiveData.value,model)
        Log.d(TAG,"size:"+model.alarmLiveData.value?.size.toString())
        binding.recylerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recylerView.adapter = adapter
        model.alarmLiveData.observe(this, Observer {
            adapter.setList(it)
        })

        binding.openSettingBtn.setOnClickListener({navigator.navigatorAlarmSetting(this,null,REQUEST_CODE)})
    }


    private fun setupPermissions() {
        //스토리지 읽기 퍼미션을 permission 변수에 담는다
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied")
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                RECORD_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==REQUEST_CODE && resultCode == Activity.RESULT_OK)
        binding.recylerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {

        when(requestCode){
            RECORD_REQUEST_CODE ->{
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {


                }else{

                }
                return
            }
        }
    }
}
