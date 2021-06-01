package com.darkabhi.covidproject.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.NewsCardBinding
import com.darkabhi.covidproject.home.news.NewsAdapter.NewsHolder
import com.darkabhi.covidproject.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsHolder>() {

    private var items = listOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding: NewsCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_card,
            parent,
            false
        )
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    companion object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem.title == newItem.title
    }

    inner class NewsHolder(private val binding: NewsCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.article = item
            binding.executePendingBindings()
        }
    }

    fun submitList(items: List<Article>) {
        this.items = items
        notifyDataSetChanged()
    }
}
