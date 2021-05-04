package com.darkabhi.covidproject.ui.fragment.info.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.FragmentSettingsBinding
import com.darkabhi.covidproject.models.UserStatus
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel by viewModels<SettingsViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        with(binding) {
            lifecycleOwner = this@SettingsFragment
            user = UserStatus(Firebase.auth.currentUser != null)
        }

        viewModel.readFromDataStore.observe(viewLifecycleOwner) {
            binding.themeSwitch.isChecked = it
        }

        binding.themeSwitch.setOnCheckedChangeListener { _, b ->
            viewModel.saveToDataStore(b)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.show()
        supportActionBar?.subtitle = ""
    }
}