package com.anujjain.getMegaAssignment.errorStates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.anujjain.getMegaAssignment.R
import com.anujjain.getMegaAssignment.databinding.FragmentErrorStateBinding

class NetworkErrorFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentErrorStateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_error_state,container,false)

        return binding.root
    }
}