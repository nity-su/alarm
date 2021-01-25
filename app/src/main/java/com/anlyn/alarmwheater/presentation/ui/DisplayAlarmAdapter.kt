package com.anlyn.alarmwheater.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.anlyn.alarmwheater.BR
import com.anlyn.domain.models.AlarmEntity
import com.anlyn.alarmwheater.R
class DisplayAlarmAdapter(private val list: List<AlarmEntity>?) : RecyclerView.Adapter<DisplayAlarmAdapter.AlarmCellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmCellViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,R.layout.alarm_item,parent,false)
        return AlarmCellViewHolder(binding)
    }

    override fun getItemCount(): Int = list?.count() ?: 0

    override fun onBindViewHolder(holder: AlarmCellViewHolder, position: Int) {
        list?.get(position)?.let { holder.bind(it) }
    }

    fun setList(){

    }

    class AlarmCellViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(any:Any){
             binding.setVariable(BR.AlarmEntity,any)
        }
    }
}