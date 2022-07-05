package com.lauravelasquezcano.melichallenge.usecases

import com.lauravelasquezcano.melichallenge.data.source.ItemsRepository
import com.lauravelasquezcano.melichallenge.utils.mockedDbItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetItemUseCaseTest {

    private var itemsRepository: ItemsRepository = mockk()

    private lateinit var getItemsUseCase: GetItemUseCase

    @Before
    fun setUp() {

        getItemsUseCase = GetItemUseCase(itemsRepository)
    }

    @After
    fun tearDown() {
        confirmVerified(itemsRepository)
    }

    @Test
    fun getItem() = runBlocking {
        coEvery { itemsRepository.getItemById("asdf") } returns mockedDbItem

        val result = getItemsUseCase.execute("asdf")

        assertEquals(mockedDbItem, result)

        coVerify(exactly = 1) {
            itemsRepository.getItemById("asdf")
        }
    }

}