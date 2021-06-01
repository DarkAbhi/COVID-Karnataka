package com.darkabhi.covidproject.home.state

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.FragmentStateBinding
import com.darkabhi.covidproject.home.HomeViewModel
import com.darkabhi.covidproject.models.DistrictData
import com.darkabhi.covidproject.models.State
import com.darkabhi.covidproject.models.Statewise
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class StateFragment : Fragment() {
    private lateinit var binding: FragmentStateBinding
    private val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var mAdapter: DistrictAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_state, container, false)

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@StateFragment
        }

        mAdapter = DistrictAdapter()

        binding.districtDataRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.indiaResponse.collectLatest {
                when (it) {
                    is State.Failed -> {
                        Timber.e(it.message)
                    }
                    is State.Loading -> {
                        binding.progressWheelCovid.visibility = View.VISIBLE
                        binding.covidDetailsLayout.visibility = View.GONE
                        binding.activeCasesLayout.visibility = View.GONE
                    }
                    is State.Success<*> -> {
                        binding.progressWheelCovid.visibility = View.GONE
                        binding.covidDetailsLayout.visibility = View.VISIBLE
                        binding.activeCasesLayout.visibility = View.VISIBLE
                        binding.state = it.data as Statewise
                    }
                    is State.Empty -> {
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.stateResponse.collectLatest {
                when (it) {
                    is State.Failed -> Timber.e(it.message)
                    is State.Loading -> {
                    }
                    is State.Success<*> -> {
                        mAdapter.submitList(it.data as List<DistrictData>)
                    }
                    is State.Empty -> {
                        Timber.i("HI")
                    }
                }
            }
        }

        return binding.root
    }
}
