package com.mgm.s1_coroutine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.mgm.s1_coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Other
    private val TAG = "coroutineTag"
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*CoroutineScope(Dispatchers.IO).launch {
            shoMyLog()
        }*/

/*        CoroutineScope(Dispatchers.Main).launch {
            */
        /**
         * do tasks synchronized
         *//*
*//*
            val time  = measureTimeMillis {
                val result1 = task1()
                val result2 = task2()
                Log.e(TAG, result1)
                Log.e(TAG, result2)
            }
            Log.e(TAG, time.toString())*//*

            */
        /**
         * do tasks Asynchronized
         *
         *//*
            val result1 = async { task1() }
            val result2 = async { task2() }
            var finalResult = ""
            val time = measureTimeMillis {
                finalResult = "${result1.await()} - ${result2.await()}"
            }
            Log.e(TAG, "$finalResult - ${time.toString()}")


        }*/

        /**
         * runBlocking
         */
        /*CoroutineScope(Dispatchers.Main).launch {
            doWork1()
            doWork1()
            doWork1()
        }

        CoroutineScope(Dispatchers.Main).launch {
            runBlocking {
                doWork2()
                delay(3000)
                doWork2()
                doWork2()
            }
        }*/

        /**
         * Custom thread name
         */
        /*CoroutineScope(newSingleThreadContext("Majid")).launch {
            Log.e(TAG, Thread.currentThread().name)
        }*/

        /**
         * change ui on IO Thread
         */
        /*CoroutineScope(Dispatchers.IO).launch {
            doWork1()
            withContext(Dispatchers.Main){
                binding.infoTxt.text = "Done"
            }
        }*/

        /**
         * repeat coroutine
         */
        /*CoroutineScope(Dispatchers.IO).launch {
            repeat(3){
                doWork1()
            }
        }*/

        /**
         * timeout coroutine
         */
        /* CoroutineScope(Dispatchers.IO).launch {
             withTimeoutOrNull(4000){
                 for (i in 1000..1100){
                     Log.e(TAG, i.toString())
                     delay(1000)
                 }
             }

             *//*val time = measureTimeMillis {
                for (i in 1000..10000){
                    Log.e(TAG, i.toString())
//                    delay(1000)
                }
            }

            Log.e(TAG, time.toString())*//*
        }*/

        /**
         * Job
         */
        /*     job = CoroutineScope(Dispatchers.Main).launch {
                 Log.e(TAG, "Coroutine working...")
                 delay(1000)
             }

     //        job.cancel()
             CoroutineScope(Dispatchers.Main).launch {
                 delay(2000)
                 Log.e(TAG, "isActive:" + job.isActive)
                 Log.e(TAG, "isCancelled:" + job.isCancelled)
                 Log.e(TAG, "isCompleted:" + job.isCompleted)
             }*/

        /**
         * Job cancel and join
         */
        /*CoroutineScope(Dispatchers.Main).launch {
            job = CoroutineScope(Dispatchers.Main).launch {
                repeat(3) {
                    Log.e(TAG, "Coroutine working inner")
                    delay(1000)
                }
            }
            delay(4000)
            job.cancelAndJoin()
            Log.e(TAG, "Coroutine working")
        }*/

        /**
         * lifeCycle
         */
        lifecycleScope.launch {
            while (true){
                Log.e(TAG,"run work ...")
                delay(1000)
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            Intent(this@MainActivity, SecondActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }


    }

    private suspend fun doWork1() {
        delay(1000)
        Log.e(TAG, "work1")
    }

    private suspend fun doWork2() {
        delay(1000)
        Log.e(TAG, "work2")
    }

    private suspend fun task1(): String {
        delay(2000)
        return "result 1"
    }

    private suspend fun task2(): String {
        delay(3000)
        return "result 2"
    }

    private suspend fun shoMyLog() {
        delay(3000)
        Log.e(TAG, Thread.currentThread().name)
    }
}