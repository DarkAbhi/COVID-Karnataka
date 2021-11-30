package com.darkabhi.covidproject.home.state

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.base.BaseFragment
import com.darkabhi.covidproject.databinding.FragmentStateBinding
import com.darkabhi.covidproject.home.HomeViewModel
import com.darkabhi.covidproject.models.NetworkState
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToast

@AndroidEntryPoint
class StateFragment : BaseFragment<FragmentStateBinding>(FragmentStateBinding::inflate) {

    private val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var districtAdapter: DistrictAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        districtAdapter = DistrictAdapter()

        binding.districtDataRecyclerView.adapter = districtAdapter

        viewModel.districtDetailResponse.observe(viewLifecycleOwner) {
            binding.districtDataRecyclerView.isVisible = it is NetworkState.Success
            binding.progressWheelCovid.isVisible = it is NetworkState.Loading
            when (it) {
                is NetworkState.Error -> {}
                is NetworkState.Loading -> {}
                is NetworkState.Success -> {
                    it.data?.let { districts ->
                        districtAdapter.submitList(districts)
                    }
                }
            }
        }

        viewModel.stateDetailResponse.observe(viewLifecycleOwner) {
            binding.casesLayout.isVisible = it is NetworkState.Success
            binding.active.isVisible = it is NetworkState.Success
            when (it) {
                is NetworkState.Error -> MotionToast.darkToast(
                    requireActivity(), "Failed ☹",
                    "An error occurred while fetching latest data.",
                    MotionToast.TOAST_ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
                )
                is NetworkState.Loading -> {}
                is NetworkState.Success -> {
                    binding.confirmedCaseCount.text = it.data?.confirmed
                    binding.recoveredCaseCount.text = it.data?.recovered
                    binding.deceasedCaseCount.text = it.data?.deceased
                    binding.active.text = getString(R.string.active_cases, it.data?.active)
                }
            }
        }
    }
}
