package com.mgm.s1_livedata_viewmodel.simple

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mgm.s1_livedata_viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Other
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnShow.setOnClickListener{
//                mainViewModel.data.value = "Hi Majid"
                mainViewModel.updateData()
            }
            val observer = Observer<String>{
                txtShow.text = it
            }
            mainViewModel.data.observe(this@MainActivity, observer)
        }
    }
}