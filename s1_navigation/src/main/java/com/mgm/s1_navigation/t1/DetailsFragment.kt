package com.mgm.s1_navigation.t1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mgm.s1_navigation.R
import com.mgm.s1_navigation.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentDetailsBinding

    //other
    private val args:DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtDetails.text = args.bundleInfo
    }

}