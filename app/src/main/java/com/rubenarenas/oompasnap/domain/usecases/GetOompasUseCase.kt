package com.rubenarenas.oompasnap.domain.usecases

import com.rubenarenas.oompasnap.data.api.Api
import com.rubenarenas.oompasnap.data.utils.createOompaMain
import com.rubenarenas.oompasnap.domain.entities.OompaMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetOompasUseCase(private val api: Api) {
    private var maxPages: Int? = 1

    suspend fun execute(page: Int): List<OompaMain>? =
        withContext(Dispatchers.IO) {
            maxPages?.takeIf { page <= it } ?: return@withContext null
            val result = api.getOompaLoompas(page)
            maxPages = result.total
            result.results?.map {
                it.createOompaMain()
            }
        }
}



