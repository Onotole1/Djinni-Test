package com.example.anatoly.djinnitest.paging

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.anatoly.djinnitest.R

/**
 * Date: 22.07.2018
 * Time: 10:55
 *
 * @author anatoly
 */
class PrimeNumberViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val text = view as TextView

    fun bind(item: Long?) {
        text.text = item.toString()
    }

    companion object {
        fun create(parent: ViewGroup): PrimeNumberViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_prime_number, parent, false)
            return PrimeNumberViewHolder(view)
        }
    }
}