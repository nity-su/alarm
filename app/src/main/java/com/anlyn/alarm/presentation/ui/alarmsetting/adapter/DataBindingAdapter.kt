package com.anlyn.alarm.presentation.ui.alarmsetting.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["first_selected"])
fun isSelected(view: View, boolean: Boolean){
    view.isSelected = boolean
}
