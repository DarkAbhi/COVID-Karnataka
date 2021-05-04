package com.darkabhi.covidproject.ui.fragment.covidresources.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.DetailsFragmentBinding
import com.darkabhi.covidproject.models.DetailsDataModel

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/29/2021.
 */
class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)
        val model: DetailsDataModel = arguments?.getSerializable("DATA_ITEM") as DetailsDataModel

        with(binding) {
            lifecycleOwner = this@DetailsFragment
            details = model
        }

        return binding.root
    }
}