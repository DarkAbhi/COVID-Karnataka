package com.darkabhi.covidproject.covidresources.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.ProviderItemBinding
import com.darkabhi.covidproject.models.ResourceData

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/29/2021.
 */
class ResourcesAdapter(
    private val onPhone: (ResourceData) -> Unit
) : PagingDataAdapter<ResourceData, ResourcesAdapter.AmbulanceHolder>(ResourcesComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmbulanceHolder {
        val binding: ProviderItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.provider_item,
            parent,
            false
        )
        return AmbulanceHolder(binding)
    }

    override fun onBindViewHolder(holder: AmbulanceHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(item) }
    }

    object ResourcesComparator : DiffUtil.ItemCallback<ResourceData>() {
        override fun areItemsTheSame(oldItem: ResourceData, newItem: ResourceData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResourceData, newItem: ResourceData): Boolean {
            return oldItem == newItem
        }
    }

    inner class AmbulanceHolder(
        private val binding: ProviderItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.phoneButton.setOnClickListener {
                onPhone(getItem(bindingAdapterPosition)!!)
            }
        }

        fun bind(item: ResourceData) {
            binding.details = item
            binding.executePendingBindings()
        }
    }
}
