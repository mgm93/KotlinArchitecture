package com.mgm.s1_hilt.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mgm.s1_hilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named
import com.mgm.s1_hilt.NAMED_APP_INFO


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivityMainBinding

    @Inject
//    @SiteName
    @Named(NAMED_APP_INFO)
    lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtInfo.text = userName
    }
}