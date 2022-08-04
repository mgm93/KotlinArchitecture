package com.mgm.s1_navigation.t2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgm.s1_navigation.R
import com.mgm.s1_navigation.databinding.FragmentCardsBinding
import com.mgm.s1_navigation.databinding.FragmentHomeBinding

class CardsFragment : Fragment() {
    //binding
    private lateinit var binding: FragmentCardsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCardsBinding.inflate(inflater,container,false)
        return binding.root
    }

}