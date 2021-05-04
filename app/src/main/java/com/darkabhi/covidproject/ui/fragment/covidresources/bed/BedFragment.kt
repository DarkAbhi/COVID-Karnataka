package com.darkabhi.covidproject.ui.fragment.covidresources.bed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.BedFragmentBinding

class BedFragment : Fragment() {

    private val viewModel by viewModels<BedViewModel>()
    private lateinit var binding: BedFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.bed_fragment, container, false)
        return binding.root

    }

}