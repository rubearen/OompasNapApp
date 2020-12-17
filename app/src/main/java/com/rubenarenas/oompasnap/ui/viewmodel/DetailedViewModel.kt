package com.rubenarenas.oompasnap.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubenarenas.oompasnap.domain.entities.OompaDetail
import com.rubenarenas.oompasnap.domain.usecases.GetOompaByIdUseCase
import kotlinx.coroutines.launch

class DetailedViewModel(val getOompa: GetOompaByIdUseCase, val idO: Int) : ViewModel() {
    val oompaDet = MutableLiveData<OompaDetail>()

    init {
        loadOompa()
    }

    private fun loadOompa() {
        viewModelScope.launch {
            oompaDet.value = getOompa.execute(idO)
        }
    }
}
