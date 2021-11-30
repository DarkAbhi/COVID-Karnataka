package com.darkabhi.covidproject.home.news

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.base.BaseFragment
import com.darkabhi.covidproject.databinding.FragmentNewsBinding
import com.darkabhi.covidproject.home.HomeViewModel
import com.darkabhi.covidproject.home.news.models.Article
import com.darkabhi.covidproject.models.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import www.sanju.motiontoast.MotionToast

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {

    private val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapter()

        binding.newsRecyclerView.adapter = newsAdapter

        viewModel.getNews()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsResponse.collect {
                    when (it) {
                        is State.Failed -> {
                            Timber.e(it.message)
                            binding.newsPb.visibility = View.GONE
                            MotionToast.darkToast(
                                requireActivity(), "Failed ☹",
                                "An error occurred while fetching latest news.",
                                MotionToast.TOAST_ERROR,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
                            )
                        }
                        is State.Loading -> {
                            binding.newsRecyclerView.visibility = View.GONE
                            binding.newsPb.visibility = View.VISIBLE
                        }
                        is State.Success<*> -> {
                            binding.newsRecyclerView.visibility = View.VISIBLE
                            binding.newsPb.visibility = View.GONE
                            newsAdapter.submitList(it.data as List<Article>)
                        }
                        is State.Empty -> {
                        }
                    }
                }
            }
        }
    }
}
