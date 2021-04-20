package com.anujjain.getMegaAssignment.trendingRepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }
}