package com.mgm.s2_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.mgm.s2_flow.databinding.ActivityCreateFlowBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class Operators4Activity : AppCompatActivity() {
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
                    simpleMergeData()
                }
            }
        }
    }

    private suspend fun simpleZipFlow(){
        val numbers = (1..3).asFlow()
        val names = listOf("Majid", "John", "Eddi").asFlow()
        numbers.zip(names){i: Int, s: String -> "$i=>$s" }
            .collect{
                binding.txtShow.append("$it\n")
            }
    }
    private suspend fun simpleZipFlowWithDelay(){
        val numbers = (1..3).asFlow().onEach { delay(2000) }
        val names = listOf("Majid", "John", "Eddi").asFlow().onEach { delay(3000) }
        numbers.zip(names){i: Int, s: String -> "$i=>$s" }
            .collect{

                binding.txtShow.append("$it\n")
            }
    }

    private suspend fun notMatchZipFlow(){
        val numbers = (1..5).asFlow()
        val names = listOf("Majid", "John", "Eddi").asFlow()
        numbers.zip(names){i: Int, s: String -> "$i=>$s" }
            .collect{
                binding.txtShow.append("$it\n")
            }
    }

    private suspend fun simpleCombineData() {
        val numbers = (1..3).asFlow().onEach { delay(2000) }
        val chars = flowOf("A", "B", "C").onEach { delay(1000) }

        numbers.combine(chars) { a, b -> "$a => $b" }
            .collect { binding.txtShow.append("$it\n") }
    }

    private suspend fun simpleMergeData(){
        //without delay
        /*val numbers = (1..3).asFlow()
        val chars = flowOf("A", "B", "C")*/
        //With delay on numbers
        /*val numbers = (1..3).asFlow().onEach { delay(1000) }
        val chars = flowOf("A", "B", "C")*/
        //With delay
        val numbers = (1..3).asFlow().onEach { delay(300) }
        val chars = flowOf("A", "B", "C").onEach { delay(500) }

        merge(numbers,chars).collect{
            binding.txtShow.append("$it\n")
        }
    }
}