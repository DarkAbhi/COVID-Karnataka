package com.darkabhi.covidproject.home.info.about

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)
        with(binding) {
            lifecycleOwner = this@AboutFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        binding.goToAbhi.setOnClickListener {
            customTabsIntent.launchUrl(
                requireContext(),
                Uri.parse("https://twitter.com/im_abhishekan")
            )
        }
        binding.goToEujin.setOnClickListener {
            customTabsIntent.launchUrl(
                requireContext(),
                Uri.parse("https://twitter.com/eujin_joseph")
            )
        }
        binding.goToKendrick.setOnClickListener {
            customTabsIntent.launchUrl(
                requireContext(),
                Uri.parse("https://twitter.com/kendgomez26")
            )
        }
        binding.goToRepo.setOnClickListener {
            customTabsIntent.launchUrl(
                requireContext(),
                Uri.parse("https://github.com/DarkAbhi/COVID-Karnataka")
            )
        }
        binding.goToWebsite.setOnClickListener {
            customTabsIntent.launchUrl(
                requireContext(),
                Uri.parse("https://covid-relief.letsbethechange.in/")
            )
        }
    }

    override fun onStart() {
        super.onStart()
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.subtitle = ""
    }
}
