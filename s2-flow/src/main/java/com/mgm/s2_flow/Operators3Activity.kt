package com.mgm.s2_flow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mgm.s2_flow.databinding.ActivityCreateFlowBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.debounce

class Operators3Activity : AppCompatActivity() {
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
                    mapFilterTake()
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private suspend fun debounceFlow() {
        flow {
            emit(100)
            delay(200)
            emit(50)
            delay(500)
            emit(2)
            delay(499)
            emit(87)
            delay(501)
        }.debounce(500)
            .collect {
                binding.txtShow.append("$it\n")
            }
    }

    private suspend fun mapFilterTakeFlow() {
        (1..10).asFlow()
            .map { it * it }
            .filter { it % 2 ==0 }
            .take(3)
            .collect{
                delay(1000)
                binding.txtShow.append("$it\n")
            }
    }

    private suspend fun mapFilterTakeFlowWithContext() {
        (1..10).asFlow()
            .map { it * it }
            .flowOn(Dispatchers.IO)
            .filter { it % 2 ==0 }
            .flowOn(Dispatchers.Main)
            .take(3)
            .flowOn(Dispatchers.IO)
            .collect{
                delay(1000)
                binding.txtShow.append("$it\n")
            }
    }

    private suspend fun mapFilterTake() {
        val listtt = (1..30).map { it * it }
            .filter { it % 2 ==0 }
            .take(5).forEach {
                binding.txtShow.append("${it/2}\n")
            }

    }
}