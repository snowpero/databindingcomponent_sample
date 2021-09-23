package com.ninis.databindingcomponent_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.ninis.databindingcomponent_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main, ClickBindingComponent(lifecycle))
        binding.clickListener = this
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    override fun onClick(v: View?) {
        Log.d("NINIS", "onClick Call!")
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
    }
}