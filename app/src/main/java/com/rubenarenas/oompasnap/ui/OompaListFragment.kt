package com.rubenarenas.oompasnap.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubenarenas.oompasnap.R
import com.rubenarenas.oompasnap.databinding.ListFragmentBinding
import com.rubenarenas.oompasnap.domain.entities.OompaMain
import com.rubenarenas.oompasnap.ui.adapter.RvAdapterList
import com.rubenarenas.oompasnap.ui.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject

class OompaListFragment() : Fragment() {
    private var currentList = mutableListOf<OompaMain>()

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var binding: ListFragmentBinding

    private val myViewModel: ListViewModel by viewModel()
    private val adapterList: RvAdapterList by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = myViewModel
        recycleStart(binding.root)
        observeChanges()

        return binding.root
    }


    private fun recycleStart(view: View) {
        layoutManager = LinearLayoutManager(context)
        myViewModel.liveData.observe(viewLifecycleOwner) { list -> adapterList.submitList(list) }
        view.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            layoutManager = this@OompaListFragment.layoutManager
            setHasFixedSize(true)
            adapter = adapterList
            addOnScrollListener(scrollListener)
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, newState: Int, dy: Int) {
            super.onScrollStateChanged(my_recycler_view, newState)
            var lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            val totalItemCount = layoutManager.itemCount
            if (totalItemCount == lastVisibleItemPosition + 1 && !myViewModel.loading) {
                myViewModel.loadNextPage()
            }
        }
    }

    private fun observeChanges() {
        myViewModel.liveData.observe(viewLifecycleOwner) { list ->
            reload(list)
        }
    }

    private fun reload(list: List<OompaMain>) {
        currentList = mutableListOf<OompaMain>().apply { addAll(list) }
        adapterList.submitList(currentList)
    }
}