package com.anlyn.alarm.presentation.ui.alarmsetting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.anlyn.alarm.BR
import com.anlyn.alarm.R

class SettingMintueAdapter() : RecyclerView.Adapter<SettingMintueAdapter.ViewHolder>(){
    private val list = Array<Int>(60) {i -> i+1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.mintue_cell,parent,false)
        return ViewHolder(
            viewBinding
        )
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val positionInList  = position % list.size
        holder.bind(list.get(positionInList ))
    }



    class ViewHolder(private val binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(mintue : Any?){
            binding.setVariable(BR.mintues,mintue)
        }
    }
}