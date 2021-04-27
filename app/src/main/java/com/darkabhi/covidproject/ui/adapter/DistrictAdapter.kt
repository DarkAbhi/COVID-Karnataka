package com.darkabhi.covidproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.DistrictCardBinding
import com.darkabhi.covidproject.models.DistrictData

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
class DistrictAdapter : RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder>() {

    private var items = listOf<DistrictData>()

    inner class DistrictViewHolder(private val binding: DistrictCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DistrictData) {
            binding.district = item
            binding.executePendingBindings()
        }
    }

    companion object : DiffUtil.ItemCallback<DistrictData>() {
        override fun areItemsTheSame(oldItem: DistrictData, newItem: DistrictData): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: DistrictData, newItem: DistrictData): Boolean = oldItem.district == newItem.district
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val binding: DistrictCardBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.district_card, parent, false)
        return DistrictViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun submitList(items: List<DistrictData>) {
        this.items = items
        notifyDataSetChanged()
    }

}