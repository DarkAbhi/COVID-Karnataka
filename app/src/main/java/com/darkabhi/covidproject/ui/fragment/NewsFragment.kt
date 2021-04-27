package com.darkabhi.covidproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.FragmentNewsBinding
import com.darkabhi.covidproject.models.State
import com.darkabhi.covidproject.ui.adapter.NewsAdapter
import com.darkabhi.covidproject.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel by viewModels<NewsViewModel>()
    private lateinit var mAdapter: NewsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@NewsFragment
        }

        mAdapter = NewsAdapter()

        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

        viewModel.newsResponse.observe(viewLifecycleOwner) {
            when (it) {
                is State.Failed -> Timber.e(it.message)
                is State.Loading -> {
                }
                is State.Success -> {
                    mAdapter.submitList(it.data.articles)
                }
            }
        }
        return binding.root
    }

}