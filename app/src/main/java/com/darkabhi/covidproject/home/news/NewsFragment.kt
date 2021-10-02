package com.darkabhi.covidproject.home.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.FragmentNewsBinding
import com.darkabhi.covidproject.home.HomeViewModel
import com.darkabhi.covidproject.models.Article
import com.darkabhi.covidproject.models.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import www.sanju.motiontoast.MotionToast

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var mAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.newsResponse.collectLatest {
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
                        mAdapter.submitList(it.data as List<Article>)
                    }
                    is State.Empty -> {
                    }
                }
            }
        }
        return binding.root
    }
}
