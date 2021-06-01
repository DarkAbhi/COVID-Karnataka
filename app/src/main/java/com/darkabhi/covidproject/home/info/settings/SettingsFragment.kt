package com.darkabhi.covidproject.home.info.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.app.preferences.Manager
import com.darkabhi.covidproject.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        with(binding) {
            lifecycleOwner = this@SettingsFragment
        }

        binding.themeSwitch.isChecked = Manager(requireContext()).isDarkTheme()

        binding.themeSwitch.setOnCheckedChangeListener { _, b ->
            if (b) AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            ) else
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            Manager(requireContext()).setDarkTheme(b)
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
