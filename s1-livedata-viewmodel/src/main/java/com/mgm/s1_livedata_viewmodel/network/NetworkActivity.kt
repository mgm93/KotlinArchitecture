package com.mgm.s1_livedata_viewmodel.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.mgm.s1_livedata_viewmodel.R
import com.mgm.s1_livedata_viewmodel.databinding.ActivityNetworkBinding

class NetworkActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityNetworkBinding

    //Other
    private val checkNetwork:CheckNetwork by lazy { CheckNetwork(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            checkNetwork.observe(this@NetworkActivity){
                Log.e("mgm", "network:$it")
                if (it) {
                    imgNetState.setImageResource(R.drawable.ic_baseline_wifi_24)
                    imgNetState.setColorFilter(ContextCompat.getColor(this@NetworkActivity,R.color.green))
                }else{
                    imgNetState.setImageResource(R.drawable.ic_baseline_wifi_off_24)
                    imgNetState.setColorFilter(ContextCompat.getColor(this@NetworkActivity,R.color.red))
                }
            }
        }
    }
}