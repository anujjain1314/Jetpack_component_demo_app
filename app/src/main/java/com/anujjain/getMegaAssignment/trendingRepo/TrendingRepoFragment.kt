package com.anujjain.getMegaAssignment.trendingRepo

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.SimpleItemAnimator
import com.anujjain.getMegaAssignment.ApiStatus
import com.anujjain.getMegaAssignment.R
import com.anujjain.getMegaAssignment.databinding.FragmentTrendingReposBinding


class TrendingRepoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTrendingReposBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_trending_repos, container, false
        )

        val viewModel = ViewModelProvider(this).get(TrendingRepoViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the TrendingRepoViewModel
        binding.viewModel = viewModel

        // Removes blinks
        (binding.sleepListRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        /**
         * observe the [viewModel.apiStatus] and navigate to [NetworkErrorFragment] when its value is [ApiStatus.ERROR]
         */
        viewModel.apiStatus.observe(viewLifecycleOwner, Observer { apiStatus ->
            if (apiStatus == ApiStatus.ERROR) {
                findNavController().navigate(R.id.action_trendingRepoFragment_to_networkErrorFragment)
            }

        })

        /**
         * observe the [viewModel.repoList] and displays new data in sleepListRecyclerView
         */
        viewModel.repoList.observe(viewLifecycleOwner, Observer { list ->
            val adapter = TrendingRepoAdapter()
            adapter.submitList(list)
            binding.sleepListRecyclerView.adapter = adapter
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