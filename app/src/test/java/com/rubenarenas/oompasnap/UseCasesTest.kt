package com.rubenarenas.oompasnap

import com.github.testcoroutinesrule.TestCoroutineRule
import com.rubenarenas.oompasnap.data.api.Api
import com.rubenarenas.oompasnap.data.datamodels.Favorite
import com.rubenarenas.oompasnap.data.datamodels.OompaData
import com.rubenarenas.oompasnap.data.datamodels.OompaResponse
import com.rubenarenas.oompasnap.domain.entities.OompaDetail
import com.rubenarenas.oompasnap.domain.entities.OompaMain
import com.rubenarenas.oompasnap.domain.usecases.GetOompaByIdUseCase
import com.rubenarenas.oompasnap.domain.usecases.GetOompasUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class UseCasesTest {


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
            favorite = com.rubenarenas.oompasnap.domain.entities.Favorite(
                color = "red", food = "nuts"
            )
        )
        Assert.assertEquals(expected, actual)
    }

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
    private lateinit var getOmpaById: GetOompaByIdUseCase
    private lateinit var getOmpas: GetOompasUseCase
    val mock: Api = Mockito.mock(Api::class.java)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getOmpaById = GetOompaByIdUseCase(api)
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

