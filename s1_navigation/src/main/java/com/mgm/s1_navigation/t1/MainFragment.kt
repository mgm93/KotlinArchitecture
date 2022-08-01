package com.mgm.s1_navigation.t1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mgm.s1_navigation.R
import com.mgm.s1_navigation.databinding.FragmentMainBinding
import java.util.zip.Inflater

class MainFragment : Fragment() {
    //binding
    private lateinit var binding:FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSend.setOnClickListener{
                val direction = MainFragmentDirections.actionMainFragmentToDetailsFragment(edtUserId.text.toString())
                findNavController().navigate(direction)
            }
        }
    }


}