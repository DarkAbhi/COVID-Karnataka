package com.darkabhi.covidproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.ProviderItemBinding
import com.darkabhi.covidproject.models.DetailsDataModel

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/29/2021.
 */
class DetailsAdapter(private val onSelect: (DetailsDataModel) -> Unit, private val onPhone: (DetailsDataModel) -> Unit) : RecyclerView.Adapter<DetailsAdapter.AmbulanceHolder>() {
    private var items = listOf<DetailsDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmbulanceHolder {
        val binding: ProviderItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.provider_item, parent, false)
        return AmbulanceHolder(binding, {
            onSelect(items[it])
        }, {
            onPhone(items[it])
        })
    }

    override fun onBindViewHolder(holder: AmbulanceHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    companion object : DiffUtil.ItemCallback<DetailsDataModel>() {
        override fun areItemsTheSame(oldItem: DetailsDataModel, newItem: DetailsDataModel): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: DetailsDataModel, newItem: DetailsDataModel): Boolean = oldItem.name == newItem.name
    }

    inner class AmbulanceHolder(private val binding: ProviderItemBinding, onSelect: (Int) -> Unit, onPhone: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onSelect(bindingAdapterPosition)
            }
            binding.phoneButton.setOnClickListener {
                onPhone(bindingAdapterPosition)
            }
        }

        fun bind(item: DetailsDataModel) {
            binding.details = item
            binding.executePendingBindings()
        }
    }

    fun submitList(items: List<DetailsDataModel>) {
        this.items = items
        notifyDataSetChanged()
    }
}