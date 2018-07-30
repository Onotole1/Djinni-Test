package com.example.anatoly.djinnitest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Date: 24.07.2018
 * Time: 10:23
 *
 * @author anatoly
 */
class PrimeNumbersAdapter: RecyclerView.Adapter<PrimeNumbersAdapter.PrimeViewHolder>() {
    var items: List<Int>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrimeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_prime_number, parent, false)

        return PrimeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: PrimeViewHolder, position: Int) {
        holder.text.text = items?.get(position).toString()
    }

    class PrimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val text = itemView as TextView
    }
}