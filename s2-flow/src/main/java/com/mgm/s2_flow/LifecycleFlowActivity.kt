package com.mgm.s2_flow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mgm.s2_flow.databinding.ActivityCreateFlowBinding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@FlowPreview
class LifecycleFlowActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityCreateFlowBinding

    //Others

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Call flow all in lifecycle
        binding.apply {
            btnShow.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    callAllInOne()
                }
            }
        }

    }

    private suspend fun callAllInOne() {
        flowOf(1, 2, 3, 4, 5, 6, 7)
            .onEach {
                //on each item
                delay(1000)
            }.onStart {
                //show loading
                binding.txtShow.append("Start\n")

            }.onCompletion {
                //hide loading
                binding.txtShow.append("Complete\n")

            }.onEmpty {
                //show empty ui
                binding.txtShow.append("empty ui\n")

            }.catch {
                //show error ui
                binding.txtShow.append("error\n")

            }.collect {
                //update ui
                binding.txtShow.append("$it\n")
            }
    }


}