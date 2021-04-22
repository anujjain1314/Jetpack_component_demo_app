package com.anujjain.getMegaAssignment.errorStates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anujjain.getMegaAssignment.ApiStatus
import com.anujjain.getMegaAssignment.R
import com.anujjain.getMegaAssignment.databinding.FragmentErrorStateBinding

class NetworkErrorFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentErrorStateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_error_state,container,false)

        val viewModel = ViewModelProvider(this).get(NetworkErrorViewModel::class.java)
        // allows the xml file to access viewmodel object
        binding.viewModel = viewModel
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this


        /**
         *
         * observe the [viewModel.eventRetryAgain] and navigate back to [TrendingRepoFragment] and calls [viewModel.onClickRetryDone()]
         */
        viewModel.eventRetryAgain.observe(viewLifecycleOwner, Observer {retryAgain->
            if(retryAgain){
                findNavController().navigate(R.id.action_networkErrorFragment_to_trendingRepoFragment)
                viewModel.onClickRetryDone()
            }

        })
        return binding.root
    }
}