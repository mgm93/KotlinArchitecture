package com.mgm.s2_flow.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.mgm.s2_flow.databinding.ActivityCreateFlowBinding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@FlowPreview
class Operators5Activity : AppCompatActivity() {
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
                    flatMapLatest()
                }
            }
        }
    }

    private fun baseNumberFlow(str : String) = flowOf(1,2,3)
        .onEach { delay(1000) }
        .map { "$it _ $str" }

    private suspend fun flatMapConcat(){
        flowOf("A", "B", "C")
            .flatMapConcat { baseNumberFlow(it) }
            .collect{binding.txtShow.append("$it\n")}
    }

    private suspend fun flatMapMerge(){
        flowOf("A", "B", "C")
//            .flatMapMerge{ baseNumberFlow(it) }
            .flatMapMerge(2) { baseNumberFlow(it) }//default 16
            .collect{binding.txtShow.append("$it\n")}
    }

    private suspend fun flatMapLatest(){
        flowOf("A", "B", "C")
            .flatMapLatest { baseNumberFlow(it) }
            .collect{binding.txtShow.append("$it\n")}
    }
}