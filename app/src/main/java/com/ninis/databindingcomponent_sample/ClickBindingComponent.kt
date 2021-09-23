package com.ninis.databindingcomponent_sample

import androidx.lifecycle.Lifecycle

class ClickBindingComponent(lifecycle: Lifecycle) : androidx.databinding.DataBindingComponent {
    private val clickBinding: ClickBindingImpl = ClickBindingImpl(lifecycle)

    override fun getClickBinding(): ClickBinding {
        return clickBinding
    }

}