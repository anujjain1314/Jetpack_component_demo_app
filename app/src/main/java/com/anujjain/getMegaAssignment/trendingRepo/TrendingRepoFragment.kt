package com.anujjain.getMegaAssignment.trendingRepo

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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