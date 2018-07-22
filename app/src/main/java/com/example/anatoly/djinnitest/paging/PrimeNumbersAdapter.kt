package com.example.anatoly.djinnitest.paging

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.anatoly.djinnitest.R

/**
 * Date: 22.07.2018
 * Time: 0:23
 *
 * @author anatoly
 */
class PrimeNumbersAdapter(private val retryCallback: () -> Unit): PagedListAdapter<Long, RecyclerView.ViewHolder>(COMPARATOR) {
    private var networkState: NetworkState? = null
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_prime_number -> (holder as PrimeNumberViewHolder).bind(getItem(position))
            R.layout.item_state_list -> (holder as NetworkStateItemViewHolder).bindTo(networkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_prime_number -> PrimeNumberViewHolder.create(parent)
            R.layout.item_state_list -> NetworkStateItemViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_state_list
        } else {
            R.layout.item_prime_number
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }


    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Long>() {
            override fun areContentsTheSame(oldItem: Long, newItem: Long): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: Long, newItem: Long): Boolean =
                    oldItem == newItem
        }
    }
}