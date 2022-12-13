package com.mgm.s2_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.mgm.s2_flow.databinding.ActivityCreateFlowBinding
import kotlinx.coroutines.flow.*

class Operators1Activity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityCreateFlowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            btnShow.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    //call suspend methods
                    takeWhileFlow()
                }
            }
        }
    }

    private suspend fun takeFlow(){
        (1..10).asFlow().take(4).collect{
            binding.txtShow.append("$it\n")
        }
    }

    private suspend fun mapFlow(){
        (1..10).asFlow().map {
            it * it
        }.collect{
            binding.txtShow.append("$it\n")
        }
    }

    private suspend fun filterFlow(){
        (1..10).asFlow().filter {
            it % 2 == 0//give even numbers
        }.collect{
            binding.txtShow.append("$it\n")
        }
    }

    private suspend fun takeWhileFlow(){
        (1..10).asFlow().takeWhile {
            it < 6
        }.collect{
            binding.txtShow.append("$it\n")
        }
    }
}