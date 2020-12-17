package com.rubenarenas.oompasnap.data.datamodels

data class OompaData(
    val id: Int,
    val last_name: String,
    val first_name: String,
    val profession: String,
    val age: Int,
    val country: String,
    val gender: String,
    val height: Int,
    val email: String,
    val image: String,
    val favorite: Favorite
)

data class Favorite(
    val color: String,
    val food: String
)