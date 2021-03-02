package com.anlyn.alarm.presentation.ui.alarmsetting

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.anlyn.alarm.databinding.ActivitySettingBinding
import com.anlyn.domain.models.AlarmEntity
import com.anlyn.domain.usercase.AddAlarmUseCase
import java.net.URI

class SettingViewModel(val addAlarmUseCase: AddAlarmUseCase,
                       private val binding:ActivitySettingBinding) : ViewModel(){
    private val TAG = SettingViewModel::class.simpleName
//    private val binding = bindingLazy.get()

    val hourLiveData = MutableLiveData<Int>()
    val minuteLiveData = MutableLiveData<Int>()
    var musicUriStr:String =""
    val alarmLiveData : LiveData<AlarmEntity> = MutableLiveData()
    init {
        Log.d(TAG,binding.hashCode().toString())
        (alarmLiveData as MutableLiveData).value = AlarmEntity()
    }
//    var alarmEntity: AlarmEntity? =null
//        get() {return field}
//        set(value) {field=value
//            map = getDayOfWeekMap(value!!)}


    fun dayOfTheWeekClicked():View.OnClickListener{
        return object : View.OnClickListener {
            override fun onClick(v: View?) {
                val btn = v as Button
                when (btn.isSelected) {
                    true -> btn.isSelected = false
                    false -> btn.isSelected = true
                }
            }
        }

    }

    fun musicPickBtnClicked(view:View){
        var activity: Activity
        if(view.context is SettingActivity)
            activity = view.context as Activity
        else
            throw Exception("activity is not SettingActivity")

        val intent_upload = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(intent_upload, SettingActivity.MUSIC_PICKER_RQ_CODE)
    }

    fun configureClicked(view:View){
        alarmLiveData.value!!.hour = hourLiveData.value ?: 0
        alarmLiveData.value!!.minute = minuteLiveData.value ?: 0
        alarmLiveData.value!!.sun = binding.sunBtn.isSelected
        alarmLiveData.value!!.mon = binding.monBtn.isSelected
        alarmLiveData.value!!.tue = binding.tueBtn.isSelected
        alarmLiveData.value!!.wed = binding.wedBtn.isSelected
        alarmLiveData.value!!.thu = binding.thuBtn.isSelected
        alarmLiveData.value!!.fri = binding.friBtn.isSelected
        alarmLiveData.value!!.sat = binding.satBtn.isSelected
        alarmLiveData.value!!.musicUriStr = musicUriStr

        if(alarmLiveData.value!!.musicUriStr.equals("")){
            return
        }

        addAlarmUseCase.save(alarmLiveData.value!!)
            .subscribe({
        Log.d("isSave:","true")
                (view.context as Activity).finish()}
            ,{
                    Log.d("isSave:","false")
//                    context.finish()
                })
    }

     fun getOnScrollListener(snapHelper: LinearSnapHelper,mLiveData: MutableLiveData<Int>): RecyclerView.OnScrollListener{

        return object : RecyclerView.OnScrollListener(){
            var lastView: TextView? = null
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    snapHelper.findSnapView(recyclerView.layoutManager)
                        .let { val cardView = it as CardView
                            val textView = cardView.getChildAt(0) as TextView
                            mLiveData.value = textView.text.toString().toInt() ?: 0
                            lastView = textView
                            textView.setTextColor(Color.BLUE)}
                } else if(lastView != null){
                    lastView?.setTextColor(Color.BLACK)
                    lastView=null
                }
            }
        }
    }


}