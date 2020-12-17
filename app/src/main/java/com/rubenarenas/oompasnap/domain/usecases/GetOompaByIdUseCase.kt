package com.rubenarenas.oompasnap.domain.usecases

import com.rubenarenas.oompasnap.data.api.Api
import com.rubenarenas.oompasnap.data.utils.createOompaDetailed
import com.rubenarenas.oompasnap.domain.entities.OompaDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetOompaByIdUseCase(private val api: Api) {
    open suspend fun execute(id: Int): OompaDetail =
        withContext(Dispatchers.IO) {
            api.getOompaLoompaById(id).createOompaDetailed()
        }
}
