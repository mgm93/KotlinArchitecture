package com.mgm.kotlinarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mgm.kotlinarchitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtMainActivity.text = "Majid Golmoradi"
    }
}