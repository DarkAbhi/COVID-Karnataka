package com.darkabhi.covidproject.covidresources.providers

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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.covidresources.adapter.LoadingStateAdapter
import com.darkabhi.covidproject.covidresources.adapter.ResourcesAdapter
import com.darkabhi.covidproject.databinding.ProvidersFragmentBinding
import com.darkabhi.covidproject.models.DashboardItemModel
import com.darkabhi.covidproject.utils.PermissionUtils.checkPhonePermission
import com.darkabhi.covidproject.utils.showShortSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ProvidersFragment : Fragment() {

    private lateinit var binding: ProvidersFragmentBinding
    private lateinit var mAdapter: ResourcesAdapter
    private val viewModel by viewModels<ProvidersViewModel>()

    private val requestPhonePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (!it) {
                // User denied permission
                binding.root.showShortSnackBar("Please grant phone permission.")
            }
        }

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.providers_fragment, container, false)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
        }

        mAdapter = ResourcesAdapter {
            if (requireActivity().checkPhonePermission())
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:${it.contactNumber}")))
            else requestPhonePermission.launch(Manifest.permission.CALL_PHONE)
        }

        binding.ambulanceRv.adapter = mAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter { mAdapter.retry() }
        )

        val model =
            arguments?.getSerializable("DASHBOARD_ITEM") as DashboardItemModel
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.show()
        supportActionBar?.title = model.title

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getData(model.title).collectLatest {
                mAdapter.submitData(it)
            }
        }

        binding.btnRetry.setOnClickListener {
            mAdapter.retry()
        }

        mAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {

                binding.btnRetry.visibility = View.GONE

                // Show ProgressBar
                binding.progressBar.visibility = View.VISIBLE
            } else {
                // Hide ProgressBar
                binding.progressBar.visibility = View.GONE

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        binding.btnRetry.visibility = View.VISIBLE
                        loadState.refresh as LoadState.Error
                    }
                    else -> null
                }
                errorState?.let {
                    Timber.e(it.error.message!!)
                }
            }
        }

        return binding.root
    }
}
