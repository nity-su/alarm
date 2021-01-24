package com.anlyn.alarmwheater.presentation.ui

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DisplayAlarmAdapter() : RecyclerView.Adapter<DisplayAlarmAdapter.MovieCellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun setList(){

    }

    class MovieCellViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(any:Any){
//            binding.set
        }
    }
}