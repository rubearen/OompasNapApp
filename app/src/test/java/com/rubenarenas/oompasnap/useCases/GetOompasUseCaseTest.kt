package com.rubenarenas.oompasnap.useCases

import com.github.testcoroutinesrule.TestCoroutineRule
import com.rubenarenas.oompasnap.data.api.Api
import com.rubenarenas.oompasnap.data.datamodels.Favorite
import com.rubenarenas.oompasnap.data.datamodels.OompaData
import com.rubenarenas.oompasnap.data.datamodels.OompaResponse
import com.rubenarenas.oompasnap.domain.entities.OompaMain

import com.rubenarenas.oompasnap.domain.usecases.GetOompasUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GetOompasUseCaseTest {

    @Test
    fun testGetOompaLoompaUseCase() {

        coEvery { api.getOompaLoompas(1) } returns OompaResponse(1, 1, listOf(oompaData))

        val result = runBlocking { getOmpas.execute(1) }
        val actual = OompaMain(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            profession = "Metal worker",
            image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg"
        )
        Assert.assertEquals(actual, result?.get(0))
    }

    @RelaxedMockK
    private lateinit var api: Api
    private lateinit var getOmpas: GetOompasUseCase


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getOmpas = GetOompasUseCase(api)
    }

    val oompaData = OompaData(
        id = 1,
        last_name = "Doe",
        first_name = "John",
        profession = "Metal worker",
        age = 22,
        country = "Loompaland",
        gender = "M",
        height = 96,
        email = "john.doe@charlie.com",
        image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg",
        favorite = Favorite(color = "red", food = "nuts")
    )
}

