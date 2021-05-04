package com.darkabhi.covidproject.ui.fragment.covidresources.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.app.AppConfig
import com.darkabhi.covidproject.databinding.CovidResourcesFragmentBinding
import com.darkabhi.covidproject.models.DashboardItemModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CovidResourcesFragment : Fragment() {
    private lateinit var binding: CovidResourcesFragmentBinding
    private lateinit var mAdapter: DashboardAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.covid_resources_fragment, container, false)

        with(binding) {
            lifecycleOwner = this@CovidResourcesFragment
        }

        mAdapter = DashboardAdapter {
            var collection = ""
            when (it.title) {
                "Ambulance Service" -> collection = AppConfig.AMBULANCE_SERVICE_COLLECTION
                "Bed Availability" -> collection = AppConfig.BED_AVAILABILITY_COLLECTION
                "Oxygen" -> collection = AppConfig.OXYGEN_COLLECTION
                "Home Testing" -> collection = AppConfig.HOME_TESTING_COLLECTION
                "Plasma Donors" -> collection = AppConfig.PLASMA_DONORS_COLLECTION
                "Blood Donors" -> collection = AppConfig.BLOOD_DONORS_COLLECTION
                "Remedesivir" -> collection = AppConfig.REMEDESIVIR_COLLECTION
                "Online Doctor Consultation" -> collection = AppConfig.ONLINE_DOC_COLLECTION
                "Tele Counselling" -> collection = AppConfig.ONLINE_DOC_COLLECTION
            }
            findNavController().navigate(R.id.resourceToAmbulanceFragment, bundleOf("DASHBOARD_ITEM" to it, "COLLECTION_NAME" to collection))
        }

        binding.dashboardItemsRecyclerview.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
        }

        val items = listOf(
                //DashboardItemModel("BBMP Info", "All the BBMP links are added here", "Anuja", 0),
                //DashboardItemModel("Helpline Numbers", "BBMP and other helpline details ", "Anuja", 0),
                DashboardItemModel("Ambulance Service", "Details of all ambulance services in Bangalore", "Deepak", 0, R.drawable.ambulance),
                DashboardItemModel("Bed Availability", "\"Details of all bed-availability of both Govt and\n" +
                        " Private hospitals verified \"", "Anusha and Ankita", 0, R.drawable.bed),
                DashboardItemModel("Oxygen", "\"Collecting and verifying all oxygen suppliers in\n" +
                        "Bangalore\"", "Nagaraju", 0, R.drawable.oxygen),
                DashboardItemModel("Home Testing", "\"Collecting and verifying \n" +
                        "Home Covid19 testing labs in Bangalore\"", "Anusha", 0, R.drawable.home),
                DashboardItemModel("Plasma Donors", "Plasma Donors details and how & where to donate", "Renuka", 0, R.drawable.plasma),
                DashboardItemModel("Blood Donors", "Blood Donors and Blood Bank details", "Renuka", 0, R.drawable.donor),
                DashboardItemModel("Remdesivir", "Remdesivir suppliers all over Bangalore", "Rahul", 0, R.drawable.remdesivir),
                DashboardItemModel("Medicine", "Medicine suppliers all over Bangalore", "Rahul", 0, R.drawable.medicine),
                DashboardItemModel("Online Doctor Consultation", "\"Online Doctor Consultation details for \n" +
                        "Home isolated patients\"", "Niranjan", 0, R.drawable.online_doc),
                DashboardItemModel("Food", "Food suppliers for Covid patients all over bangalore", "Gouthami", 0, R.drawable.food),
                //DashboardItemModel("General Covid19 Precautions", "General Precautions, Home isolation measures and infos", "Rahul", 0),
                //DashboardItemModel("Fact Check", "\"Fact checking about fake message forwarded about \n" +
                //       "Vaccination and Covid-19\"", "Anuja", 0),
                //DashboardItemModel("Vaccination Drive", "Vaccination Drive and Information about it", "Anusha", 0),
                //DashboardItemModel("Home Isolation", "Home Isolation remedies and informations", "Anuja", 0),
                DashboardItemModel("Tele Counselling", "Mental Health Consulation for Home isolated patients", "Gouthami", 0, R.drawable.tele),
                //DashboardItemModel("Gadgets Related", "\"Covid resources gadget details like Oxy concentrator \n" +
                //        "and other details\"", "Nagaraju", 0),
        )

        mAdapter.submitList(items)

        return binding.root
    }
}