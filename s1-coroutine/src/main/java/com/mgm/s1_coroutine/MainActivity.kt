package com.mgm.s1_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mgm.s1_coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding:ActivityMainBinding

    //Other
    private val TAG = "coroutineTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            shoMyLog()
        }
    }

    private suspend fun shoMyLog(){
        delay(3000)
        Log.e(TAG, Thread.currentThread().name)
    }
}