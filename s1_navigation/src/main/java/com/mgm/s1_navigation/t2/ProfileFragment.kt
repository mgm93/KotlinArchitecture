package com.mgm.s1_navigation.t2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgm.s1_navigation.R
import com.mgm.s1_navigation.databinding.FragmentHomeBinding
import com.mgm.s1_navigation.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    //binding
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

}