package com.mgm.s2_flow.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mgm.s2_flow.databinding.ActivityCreateFlowBinding
import kotlinx.coroutines.flow.*

class Operators2Activity : AppCompatActivity() {
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
                    foldFlow()
                }
            }
        }
    }

    private suspend fun repeatFlow() {
        flow {
            repeat(3) {
//                (1..4).forEach { emit(it) }
                flowOf(1, 2, 3, 4)
                    .collect { emit(it) }
            }
        }.collect {
            binding.txtShow.append("$it\n")
        }
    }

    private suspend fun countFlow() {
        val count = (1..21).count {
            (it % 2 == 0).not()//give odd numbers
        }
        binding.txtShow.append("$count\n")
    }

    private suspend fun reduceFlow(){
        val reduce = (1..5).asFlow().reduce{
            accumulator, value -> accumulator + value
        }
        binding.txtShow.append("$reduce\n")
    }
    private suspend fun reduceStringFlow(){
        val reduce = flowOf("Majid", "Golmoradi", "Scott", "John").reduce{
                accumulator, value -> accumulator + value
        }
        binding.txtShow.append("$reduce\n")
    }

    private suspend fun foldFlow(){
        val fold = (1..5).asFlow().fold(100){
            acc, value -> acc + value
        }
        binding.txtShow.append("$fold\n")
    }
}