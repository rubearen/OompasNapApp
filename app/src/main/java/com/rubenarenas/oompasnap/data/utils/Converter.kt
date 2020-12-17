package com.rubenarenas.oompasnap.data.utils

import androidx.lifecycle.MutableLiveData
import com.rubenarenas.oompasnap.data.datamodels.OompaData
import com.rubenarenas.oompasnap.domain.entities.Favorite
import com.rubenarenas.oompasnap.domain.entities.OompaDetail
import com.rubenarenas.oompasnap.domain.entities.OompaMain

fun OompaData.createOompaMain() = OompaMain(
    id = id,
    firstName = first_name,
    lastName = last_name,
    profession = profession,
    image = image
)

 fun OompaData.createOompaDetailed() = OompaDetail(
    id = id,
    firstName = first_name,
    lastName = last_name,
    profession = profession,
    image = image,
    gender = gender,
    height = height,
    country = country,
    age = age,
    email = email,
    favorite = Favorite(
        color = favorite.color,
        food = favorite.food
    )
)

fun <T> MutableLiveData<MutableList<T>>.addItemsToList(items: List<T>?) {
    val value = this.value ?: mutableListOf()
    if (items != null) {
        value.addAll(items)
    }
    this.value = value
}




