package com.darkabhi.covidproject.home.state

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.data.room.models.DistrictDetail
import com.darkabhi.covidproject.databinding.DistrictCardBinding

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
class DistrictAdapter :
    ListAdapter<DistrictDetail, DistrictAdapter.DistrictViewHolder>(DistrictComparator()) {

    inner class DistrictViewHolder(private val binding: DistrictCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DistrictDetail) {
            binding.districtLocation.text = item.district
            binding.districtActive.text =
                binding.root.context.getString(R.string.active, item.active)
            binding.districtConfirmed.text =
                binding.root.context.getString(R.string.confirmed, item.confirmed)
            binding.districtDeceased.text =
                binding.root.context.getString(R.string.deceased, item.deceased)
            binding.districtRecovered.text =
                binding.root.context.getString(R.string.recovered, item.recovered)
        }
    }

    class DistrictComparator : DiffUtil.ItemCallback<DistrictDetail>() {
        override fun areItemsTheSame(oldItem: DistrictDetail, newItem: DistrictDetail): Boolean =
            oldItem.district === newItem.district

        override fun areContentsTheSame(oldItem: DistrictDetail, newItem: DistrictDetail): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val binding =
            DistrictCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DistrictViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null)
            holder.bind(currentItem)
    }
}
