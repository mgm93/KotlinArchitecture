package com.mgm.s2_flow.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mgm.s2_flow.databinding.ActivityCreateFlowBinding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@FlowPreview
class CreateFlowActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityCreateFlowBinding

    //Others
    private lateinit var staticNamesFlow: Flow<String>
    private lateinit var dynamicNumbersFlow: Flow<Int>
    private lateinit var collectionFlow : Flow<Int>
    private val myList = listOf(1,2,3,4,5,6,7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //callMethods
        sutUpStaticNames()
        setUpDynamicNumbers()
        setUpCollectionFlow()

        binding.apply {
            btnShow.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    //simpleIntFlow()
                   /* collectionFlow.collect{
                        binding.txtShow.append("$it\n")
                    }*/
                    convertFunToFlow()
                }
            }
        }
    }

    private suspend fun simpleIntFlow() {
        flowOf(1, 2, 3, 4, 5).collect {
            binding.txtShow.append("$it\n")
        }
    }

    private fun sutUpStaticNames() {
        staticNamesFlow = flowOf("Majid", "Mohammad")
    }

    private fun setUpDynamicNumbers() {
        dynamicNumbersFlow = flow {
            (1..5).forEach {
                delay(1000)
                emit(it)
            }
        }
    }

    private fun setUpCollectionFlow(){
        collectionFlow = myList.asFlow()
    }

    private fun getUserInfo(): String {
        return "Majid Golmoradi, Age is 29"
    }
    private suspend fun convertFunToFlow(){
        ::getUserInfo.asFlow().collect{
            binding.txtShow.append("$it\n")
        }
    }
}