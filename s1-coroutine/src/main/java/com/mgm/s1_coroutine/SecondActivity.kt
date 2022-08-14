package com.mgm.s1_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mgm.s1_coroutine.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}