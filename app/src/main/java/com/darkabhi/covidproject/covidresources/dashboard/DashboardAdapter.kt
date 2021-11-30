package com.darkabhi.covidproject.covidresources.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.covidresources.dashboard.models.DashboardItemModel
import com.darkabhi.covidproject.databinding.DashboardGridItemBinding

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/28/2021.
 */
class DashboardAdapter(private val onSelect: (DashboardItemModel) -> Unit) :
    RecyclerView.Adapter<DashboardAdapter.DashboardHolder>() {

    private var items = listOf<DashboardItemModel>()

    inner class DashboardHolder(
        private val binding: DashboardGridItemBinding,
        onSelect: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onSelect(bindingAdapterPosition)
            }
        }

        fun bind(item: DashboardItemModel) {
            binding.dashboardItem = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHolder {
        val binding: DashboardGridItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.dashboard_grid_item,
            parent,
            false
        )
        return DashboardHolder(binding) {
            onSelect(items[it])
        }
    }

    override fun onBindViewHolder(holder: DashboardHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun submitList(items: List<DashboardItemModel>) {
        this.items = items
        notifyDataSetChanged()
    }
}
