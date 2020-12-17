package com.rubenarenas.oompasnap.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubenarenas.oompasnap.data.utils.addItemsToList
import com.rubenarenas.oompasnap.domain.entities.OompaMain
import com.rubenarenas.oompasnap.domain.usecases.GetOompasUseCase
import kotlinx.coroutines.launch


class ListViewModel(val getOompas: GetOompasUseCase) : ViewModel() {
    val liveData = MutableLiveData<MutableList<OompaMain>>()
    var loading = false

    private var page = 1

    init {
        start()
    }

    fun loadOompas() {
        loading = true

        viewModelScope.launch {
            getOompas.execute(page)?.let {
                liveData.addItemsToList(it)
            }
            loading = false
        }
    }

    fun start() {
        liveData.value?.clear()
        page = 1
        loadOompas()
    }

    fun loadNextPage() {
        page++
        loadOompas()
    }
}





