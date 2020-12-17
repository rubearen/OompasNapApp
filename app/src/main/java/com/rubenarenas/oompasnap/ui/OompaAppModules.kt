package com.rubenarenas.oompasnap.ui

import com.rubenarenas.oompasnap.domain.usecases.GetOompaByIdUseCase
import com.rubenarenas.oompasnap.domain.usecases.GetOompasUseCase
import com.rubenarenas.oompasnap.ui.adapter.RvAdapterList
import com.rubenarenas.oompasnap.ui.viewmodel.DetailedViewModel
import com.rubenarenas.oompasnap.ui.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ListViewModel(get()) }
    viewModel { (id: Int) -> DetailedViewModel(get(), idO = id) }
    factory { GetOompasUseCase(get()) }
    factory { GetOompaByIdUseCase(get()) }
    factory { RvAdapterList() }

}