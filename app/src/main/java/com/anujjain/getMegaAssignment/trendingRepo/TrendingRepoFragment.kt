package com.anujjain.getMegaAssignment.trendingRepo

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anujjain.getMegaAssignment.R
import com.anujjain.getMegaAssignment.databinding.FragmentTrendingReposBinding

class TrendingRepoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentTrendingReposBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_trending_repos,container,false)

        val viewModel = ViewModelProvider(this).get(TrendingRepoViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the TrendingRepoViewModel
        binding.viewModel = viewModel

        viewModel.errorFoundEvent.observe(viewLifecycleOwner, Observer { errorFound ->
            if(errorFound == true){
                findNavController().navigate(R.id.action_trendingRepoFragment_to_networkErrorFragment)
            }

        })

        setHasOptionsMenu(true)

        return binding.root
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.trending_repo_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}