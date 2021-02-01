package com.anlyn.alarmwheater.presentation.ui.alarmsetting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.anlyn.alarmwheater.BR
import com.anlyn.alarmwheater.R

class SettingHourAdapter() : RecyclerView.Adapter<SettingHourAdapter.ViewHolder>() {
    private val list = Array<Int>(12){i -> i+1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.hour_cell,parent,false)
        return ViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.get(position % list.size))
    }

    class ViewHolder(val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root){
        fun bind(any: Any){
            viewDataBinding.setVariable(BR.hour,any)
        }
    }
}