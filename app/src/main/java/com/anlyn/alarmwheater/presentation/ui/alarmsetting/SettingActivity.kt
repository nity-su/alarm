package com.anlyn.alarmwheater.presentation.ui.alarmsetting

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.anlyn.alarmwheater.databinding.ActivitySettingBinding
import javax.inject.Inject


class SettingActivity : AppCompatActivity() {
    companion object{
       const val TO_ONE = 4;
    }
    @Inject
    lateinit var viewModel:SettingViewModel
    val TAG = SettingActivity::class.simpleName
    lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timeSettingRcleInit( binding.mintueSettingRcle)
        timeSettingRcleInit(binding.hourSettingRcle)
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