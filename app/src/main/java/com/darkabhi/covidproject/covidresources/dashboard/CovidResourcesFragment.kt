package com.darkabhi.covidproject.covidresources.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.covidresources.dashboard.models.DashboardItemModel
import com.darkabhi.covidproject.databinding.CovidResourcesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CovidResourcesFragment : Fragment() {
    private lateinit var binding: CovidResourcesFragmentBinding
    private lateinit var mAdapter: DashboardAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.covid_resources_fragment, container, false)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
        }

        mAdapter = DashboardAdapter {
            findNavController().navigate(
                R.id.action_covidResourcesFragment_to_resourcesFragment,
                bundleOf("DASHBOARD_ITEM" to it)
            )
        }

        binding.dashboardItemsRecyclerview.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
        }

        val items = listOf(
            DashboardItemModel(
                "Ambulance Service",
                "Details of all ambulance services in Bangalore",
                R.drawable.ambulance
            ),
            DashboardItemModel(
                "Oxygen",
                "\"Collecting and verifying all oxygen suppliers in\n" +
                    "Bangalore\"",
                R.drawable.oxygen
            ),
            DashboardItemModel(
                "Home Testing",
                "\"Collecting and verifying \n" +
                    "Home Covid19 testing labs in Bangalore\"",
                R.drawable.home
            ),
            DashboardItemModel(
                "Blood Donors",
                "Blood Donors and Blood Bank details",
                R.drawable.donor
            ),
            /*DashboardItemModel(
                "Medicine",
                "Medicine suppliers all over Bangalore",

                R.drawable.medicine
            ),*/
            DashboardItemModel(
                "Online Doctor Consultation",
                "\"Online Doctor Consultation details for \n" +
                    "Home isolated patients\"",
                R.drawable.online_doc
            ),
            DashboardItemModel(
                "Food",
                "Food suppliers for Covid patients all over bangalore",

                R.drawable.food
            ),
            DashboardItemModel(
                "Tele Counselling",
                "Mental Health Consulation for Home isolated patients",
                R.drawable.tele
            )
        )

        mAdapter.submitList(items)

        return binding.root
    }
}
