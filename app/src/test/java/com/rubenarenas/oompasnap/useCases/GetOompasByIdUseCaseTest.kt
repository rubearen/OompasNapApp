package com.rubenarenas.oompasnap.useCases

import com.github.testcoroutinesrule.TestCoroutineRule
import com.rubenarenas.oompasnap.data.api.Api
import com.rubenarenas.oompasnap.data.datamodels.OompaData
import com.rubenarenas.oompasnap.domain.entities.Favorite
import com.rubenarenas.oompasnap.domain.entities.OompaDetail
import com.rubenarenas.oompasnap.domain.usecases.GetOompaByIdUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetOompasByIdUseCaseTest {

    @Test
    fun testGetOompaLoompaByIdSuccess() {
        coEvery { api.getOompaLoompaById(1) } returns oompaData

        val actual = runBlocking { getOmpaById.execute(1) }
        val expected = OompaDetail(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            profession = "Metal worker",
            image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg",
            gender = "M",
            height = 96,
            country = "Loompaland",
            age = 22,
            email = "john.doe@charlie.com",
            favorite = Favorite(
                color = "red", food = "nuts"
            )
        )
        Assert.assertEquals(expected, actual)
    }

    @RelaxedMockK
    private lateinit var api: Api
    private lateinit var getOmpaById: GetOompaByIdUseCase

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getOmpaById = GetOompaByIdUseCase(api)
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
        favorite = com.rubenarenas.oompasnap.data.datamodels.Favorite(color = "red", food = "nuts")
    )
}