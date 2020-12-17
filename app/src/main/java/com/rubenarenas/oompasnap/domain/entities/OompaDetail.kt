package com.rubenarenas.oompasnap.domain.entities

data class OompaDetail(
    val id: Int,
    val lastName: String,
    val firstName: String,
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
