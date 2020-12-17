package com.rubenarenas.oompasnap.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.rubenarenas.oompasnap.R
import com.rubenarenas.oompasnap.databinding.OompaDetailsFragmentBindingImpl
import com.rubenarenas.oompasnap.ui.viewmodel.DetailedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

import org.koin.core.parameter.parametersOf

class OompaDetailsFragment(val idO: Int) : Fragment() {

    private lateinit var binding: OompaDetailsFragmentBindingImpl
    private val viewModel: DetailedViewModel by viewModel() { parametersOf(idO) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.oompa_details_fragment, container, false)
        binding.lifecycleOwner = this
        viewModel.oompaDet.observe(viewLifecycleOwner) { it -> binding.oompaDetail = it }
        return binding.root
    }


}