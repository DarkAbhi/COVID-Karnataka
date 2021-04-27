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
import com.darkabhi.covidproject.databinding.FragmentHomeBinding
import com.darkabhi.covidproject.models.State
import com.darkabhi.covidproject.ui.adapter.DistrictAdapter
import com.darkabhi.covidproject.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeFragmentViewModel>()
    private lateinit var mAdapter: DistrictAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@HomeFragment
        }

        mAdapter = DistrictAdapter()

        binding.districtDataRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

        viewModel.indiaResponse.observe(viewLifecycleOwner) {
            when (it) {
                is State.Failed -> {
                }
                is State.Loading -> {
                }
                is State.Success -> {
                    binding.state = it.data
                }
            }
        }
        viewModel.stateResponse.observe(viewLifecycleOwner) {
            when (it) {
                is State.Failed -> Timber.e("FAILED ${it.message}")
                is State.Loading -> Timber.i("LOADING")
                is State.Success -> {
                    mAdapter.submitList(it.data)
                }
            }
        }

        return binding.root
    }
}