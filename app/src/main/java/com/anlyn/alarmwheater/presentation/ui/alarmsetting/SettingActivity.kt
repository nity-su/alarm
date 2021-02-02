package com.anlyn.alarmwheater.presentation.ui.alarmsetting

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.anlyn.alarmwheater.databinding.ActivitySettingBinding
import com.anlyn.domain.models.AlarmEntity
import java.io.Serializable


class SettingActivity : AppCompatActivity() {
    companion object{
       const val TO_ONE = 4;

        fun getCallingIntent(context: Context, alarmEntity: AlarmEntity?): Intent {
            val intent = Intent(context,SettingActivity::class.java)
            if(alarmEntity!=null)
            intent.putExtra("alarmEntity",alarmEntity as Serializable)

            return intent }

    }

    val viewModel : SettingViewModel by viewModels()
    val TAG = SettingActivity::class.simpleName
    lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timeSettingRcleInit( binding.mintueSettingRcle)
        timeSettingRcleInit(binding.hourSettingRcle)
        btn_init()

    }

    private fun timeSettingRcleInit(recyclerView: RecyclerView){
        val snapHelper = LinearSnapHelper()

        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener( getOnScrollListener(snapHelper))

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

            when(recyclerView){
                binding.hourSettingRcle -> {
                    recyclerView.adapter = SettingHourAdapter()
                    recyclerView.scrollToPosition(Integer.MAX_VALUE/2-TO_ONE +9)}
                binding.mintueSettingRcle -> {
                    recyclerView.adapter = SettingMintueAdapter()
                    recyclerView.scrollToPosition(Integer.MAX_VALUE/2-TO_ONE +19)}
            }
    }

    fun btn_init(){
        intent.getSerializableExtra("alarmEntity").let {
            if (it != null) {
                viewModel.alarmEntity = it as AlarmEntity
                }else{
                viewModel.alarmEntity = AlarmEntity()
            }
        }

        binding.dayOfTheWeekTr.let{
            for(v in it.children){
                val btn = v as Button
                btn.setOnClickListener(
                    viewModel.dayOfTheWeekClicked(btn)
                )
            }
        }

        binding.configBtn.setOnClickListener({})
    }

    private fun getOnScrollListener(snapHelper: LinearSnapHelper): RecyclerView.OnScrollListener{

        return object : RecyclerView.OnScrollListener(){
            var lastView:TextView? = null
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    snapHelper.findSnapView(recyclerView.layoutManager)
                        .let { val cardView = it as CardView
                            val textView = cardView.getChildAt(0) as TextView
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