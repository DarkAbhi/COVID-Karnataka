package com.darkabhi.covidproject.ui.fragment.covidresources.providers

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.ProvidersFragmentBinding
import com.darkabhi.covidproject.models.DashboardItemModel
import com.darkabhi.covidproject.models.State
import com.darkabhi.covidproject.ui.adapter.DetailsAdapter
import com.darkabhi.covidproject.utils.PermissionUtils.checkPhonePermission
import com.darkabhi.covidproject.utils.showShortSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class ProvidersFragment : Fragment() {

    private lateinit var binding: ProvidersFragmentBinding
    private lateinit var mAdapter: DetailsAdapter
    private val viewModel by viewModels<ProvidersViewModel>()

    private val requestPhonePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (!it) {
            // User denied permission
            binding.root.showShortSnackBar("Please grant phone permission.")
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.providers_fragment, container, false)

        with(binding) {
            lifecycleOwner = this@ProvidersFragment
        }

        mAdapter = DetailsAdapter({ findNavController().navigate(R.id.ambulanceToEditAmbulance, bundleOf("DATA_ITEM" to it)) }, {
            if (requireActivity().checkPhonePermission())
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:${it.contact_number}")))
            else requestPhonePermission.launch(Manifest.permission.CALL_PHONE)
        })

        binding.ambulanceRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchAmbulanceData(arguments?.getString("COLLECTION_NAME")!!).collect {
                when (it) {
                    is State.Failed -> Timber.e(it.message)
                    is State.Loading -> {
                    }
                    is State.Success -> {
                        mAdapter.submitList(it.data!!)
                    }
                }
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val model: DashboardItemModel = arguments?.getSerializable("DASHBOARD_ITEM") as DashboardItemModel
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.show()
        supportActionBar?.title = model.title
        supportActionBar?.subtitle = "Team Lead: ${model.teamLead}"
    }
}