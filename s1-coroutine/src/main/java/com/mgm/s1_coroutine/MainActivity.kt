package com.mgm.s1_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mgm.s1_coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding:ActivityMainBinding

    //Other
    private val TAG = "coroutineTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        /*CoroutineScope(Dispatchers.IO).launch {
            shoMyLog()
        }*/

        CoroutineScope(Dispatchers.IO).launch {
            /**
             * do tasks synchronized
             */
/*
            val time  = measureTimeMillis {
                val result1 = task1()
                val result2 = task2()
                Log.e(TAG, result1)
                Log.e(TAG, result2)
            }
            Log.e(TAG, time.toString())*/

            /**
             * do tasks Asynchronized
             *
             */
            val result1 = async { task1() }
            val result2 = async { task2() }
            var finalResult = ""
            val time = measureTimeMillis {
                finalResult = "${result1.await()} - ${result2.await()}"
            }
            Log.e(TAG, "$finalResult - ${time.toString()}")


        }
    }

    private suspend fun task1():String{
        delay(2000)
        return "result 1"
    }

    private suspend fun task2():String{
        delay(3000)
        return "result 2"
    }

    private suspend fun shoMyLog(){
        delay(3000)
        Log.e(TAG, Thread.currentThread().name)
    }
}