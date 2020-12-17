package com.rubenarenas.oompasnap


import androidx.lifecycle.LiveData
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Assert.*
import com.rubenarenas.oompasnap.data.datamodels.OompaData
import com.rubenarenas.oompasnap.data.utils.addItemsToList
import com.rubenarenas.oompasnap.data.utils.createOompaDetailed
import com.rubenarenas.oompasnap.data.utils.createOompaMain
import com.rubenarenas.oompasnap.domain.entities.Favorite
import com.rubenarenas.oompasnap.domain.entities.OompaDetail
import com.rubenarenas.oompasnap.domain.entities.OompaMain

import org.junit.Rule
import org.junit.Test


class ConverterTest {
    @Test
    fun testCreateOompaMain() {
        val actual = oompaData.createOompaMain()
        val expected = OompaMain(
            id = 3,
            firstName = "Cat",
            lastName = "Felix",
            profession = "developer",
            image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg"
        )

        assertEquals(expected, actual)
    }

    @Test
    fun testCreateOompaDetailed() {
        val actual = oompaData.createOompaDetailed()
        val expected = OompaDetail(
            id = 3,
            firstName = "Cat",
            lastName = "Felix",
            profession = "developer",
            image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg",
            gender = "M",
            height = 140,
            country = "USA",
            age = 14,
            email = "cat@gmail.com",
            favorite = Favorite(
                color = "red",
                food = "apple"
            )

        )

        assertEquals(expected, actual)
    }

    @Test
    fun testAddItemsToList() {

        val mutableLiveData = MutableLiveData<MutableList<OompaMain>>()
        mutableLiveData.addItemsToList(value)

        mutableLiveData.observeForTesting { assertEquals(value, mutableLiveData.value) }
        //  assertEquals(value, mutableLiveData.getOrAwaitValue())
    }


    val oompaData = OompaData(
        id = 3,
        last_name = "Felix",
        first_name = "Cat",
        profession = "developer",
        age = 14,
        country = "USA",
        gender = "M",
        height = 140,
        email = "cat@gmail.com",
        image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg",
        favorite = com.rubenarenas.oompasnap.data.datamodels.Favorite(color = "red", food = "apple")
    )

    val value = mutableListOf<OompaMain>(
        oompaData.createOompaMain(),
        oompaData.createOompaMain(),
        oompaData.createOompaMain()
    )

    @get:Rule
    val rule = InstantTaskExecutorRule()

    fun <T> LiveData<T>.observeForTesting(block: () -> Unit) {
        val observer = Observer<T> { }
        try {
            observeForever(observer)
            block()
        } finally {
            removeObserver(observer)
        }
    }
}

