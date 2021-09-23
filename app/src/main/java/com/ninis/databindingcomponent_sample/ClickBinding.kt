package com.ninis.databindingcomponent_sample

import android.view.View
import androidx.databinding.BindingAdapter

interface ClickBinding {
    @BindingAdapter("onClick")
    fun setOnClickListener(view: View, onClickListener: View.OnClickListener)
}