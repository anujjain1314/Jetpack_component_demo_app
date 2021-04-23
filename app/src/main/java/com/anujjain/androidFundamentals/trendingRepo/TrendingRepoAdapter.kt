package com.anujjain.androidFundamentals.trendingRepo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anujjain.androidFundamentals.databinding.RowItemTrendingReposBinding
import com.anujjain.androidFundamentals.database.TrendingRepoDataModel

class TrendingRepoAdapter : ListAdapter<TrendingRepoDataModel,TrendingRepoAdapter.TrendingRepoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingRepoViewHolder {
        return TrendingRepoViewHolder(RowItemTrendingReposBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TrendingRepoViewHolder, position: Int) {
        val dataModel = getItem(position)
        holder.bind(dataModel)
        holder.itemView.setOnClickListener { v ->
            // Get the current state of the item
            val expanded: Boolean = dataModel.expanded
            // Change the state
            dataModel.expanded = !expanded
            // Notify the adapter that item has changed
            notifyItemChanged(position)
        }
    }

    class TrendingRepoViewHolder(val itemBinding : RowItemTrendingReposBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(dataModel : TrendingRepoDataModel){
            itemBinding.dataModel = dataModel

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            itemBinding.executePendingBindings()
        }
    }



    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [TrendingRepoDataModel]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<TrendingRepoDataModel>() {
        override fun areItemsTheSame(oldItem: TrendingRepoDataModel, newItem: TrendingRepoDataModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TrendingRepoDataModel, newItem: TrendingRepoDataModel): Boolean {
            return oldItem.url == newItem.url
        }
    }
}