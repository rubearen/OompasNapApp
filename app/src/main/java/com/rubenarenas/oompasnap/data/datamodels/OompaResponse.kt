package com.rubenarenas.oompasnap.data.datamodels


data class OompaResponse(
    val current: Int,
    val total: Int,
    val results: List<OompaData>?
)