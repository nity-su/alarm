package com.anlyn.alarm.presentation.ui.alarmsetting

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.anlyn.alarm.BR
import com.anlyn.alarm.databinding.ActivitySettingBinding
import com.anlyn.alarm.presentation.ui.alarmsetting.adapter.SettingHourAdapter
import com.anlyn.alarm.presentation.ui.alarmsetting.adapter.SettingMintueAdapter
import com.anlyn.domain.models.AlarmEntity
import dagger.android.AndroidInjection
import java.io.Serializable
import javax.inject.Inject


class SettingActivity : AppCompatActivity() {
    companion object{
       const val TO_ONE = 4;
       const val MUSIC_PICKER_RQ_CODE =1001
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
        binding.setVariable(BR.model,viewModel)
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
                    recyclerView.adapter =
                        SettingHourAdapter()
                    recyclerView.scrollToPosition(Integer.MAX_VALUE/2-TO_ONE +9)}
                binding.mintueSettingRcle -> {
                    recyclerView.addOnScrollListener( viewModel.getOnScrollListener(snapHelper,viewModel.minuteLiveData))
                    recyclerView.adapter =
                        SettingMintueAdapter()
                    recyclerView.scrollToPosition(Integer.MAX_VALUE/2-TO_ONE +19)}
            }
    }

    fun btn_init(){

        binding.dayOfTheWeekTr.let{
            for(v in it.children){
                val btn = v as Button
                btn.setOnClickListener(
                    viewModel.dayOfTheWeekClicked()
                )
            }
        }

        binding.configBtn.setOnClickListener({ viewModel.configureClicked(binding) })

        binding.musicPickerBtn.setOnClickListener({
            val intent_upload = Intent(RingtoneManager.ACTION_RINGTONE_PICKER).apply {

                apply@this.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM)
                apply@this.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone")
                apply@this.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, "currentTone")
                apply@this.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false)
                apply@this.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true)
                apply@this.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,RingtoneManager.TYPE_NOTIFICATION);
            }
            SettingActivity@this.startActivityForResult(intent_upload, MUSIC_PICKER_RQ_CODE)
        })
    }

    fun setMusicUri(uri:Uri){
        viewModel.uri = uri!!
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == MUSIC_PICKER_RQ_CODE)
        {
            if(resultCode == Activity.RESULT_OK){
                setMusicUri(data?.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)!!)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
