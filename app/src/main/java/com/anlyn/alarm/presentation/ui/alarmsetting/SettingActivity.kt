package com.anlyn.alarm.presentation.ui.alarmsetting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.anlyn.alarm.BR
import com.anlyn.alarm.databinding.ActivitySettingBinding
import com.anlyn.alarm.presentation.entities.Alarm
import com.anlyn.domain.models.AlarmEntity
import dagger.android.AndroidInjection
import java.io.Serializable
import javax.inject.Inject


class SettingActivity : AppCompatActivity() {
    companion object{
       const val TO_ONE = 4;

        fun getCallingIntent(context: Context, alarmEntity: AlarmEntity?): Intent {
            val intent = Intent(context,SettingActivity::class.java)
            if(alarmEntity!=null)
            intent.putExtra("alarmEntity",alarmEntity as Serializable)

            return intent }
    }
    @Inject lateinit var factory:SettingVMFactory
    val viewModel : SettingViewModel by viewModels(){factory}
    val TAG = SettingActivity::class.simpleName
    lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.setVariable(BR.AlarmEntity,viewModel.alarmLiveData.value)
        alarmEntity_init()
        timeSettingRcleInit( binding.mintueSettingRcle)
        timeSettingRcleInit(binding.hourSettingRcle)
        btn_init()

    }

    fun alarmEntity_init(){
        intent.getSerializableExtra("alarmEntity").let {
            var alarmEntity:AlarmEntity
            if (it != null) {
                (viewModel.alarmLiveData as MutableLiveData).value = it as AlarmEntity
            }else{
                (viewModel.alarmLiveData as MutableLiveData).value  = AlarmEntity()
            }
        }
    }

    private fun timeSettingRcleInit(recyclerView: RecyclerView){
        val snapHelper = LinearSnapHelper()

        snapHelper.attachToRecyclerView(recyclerView)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

            when(recyclerView){
                binding.hourSettingRcle -> {
                    recyclerView.addOnScrollListener( viewModel.getOnScrollListener(snapHelper,viewModel.hourLiveData))
                    recyclerView.adapter = SettingHourAdapter()
                    recyclerView.scrollToPosition(Integer.MAX_VALUE/2-TO_ONE +9)}
                binding.mintueSettingRcle -> {
                    recyclerView.addOnScrollListener( viewModel.getOnScrollListener(snapHelper,viewModel.mintueLiveData))
                    recyclerView.adapter = SettingMintueAdapter()
                    recyclerView.scrollToPosition(Integer.MAX_VALUE/2-TO_ONE +19)}
            }
    }

    fun btn_init(){

        binding.dayOfTheWeekTr.let{
            for(v in it.children){
                val btn = v as Button
                btn.setOnClickListener(
                    viewModel.dayOfTheWeekClicked(btn)
                )
            }
        }

        binding.configBtn.setOnClickListener({viewModel.configureClicked(this)})
    }
    @BindingAdapter(value = ["is_selected"])
    fun isSelected(view: View, boolean: Boolean){
        view.isSelected = boolean
    }
}