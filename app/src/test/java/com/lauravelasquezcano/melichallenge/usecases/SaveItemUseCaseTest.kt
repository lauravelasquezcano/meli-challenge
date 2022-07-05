package com.lauravelasquezcano.melichallenge.usecases

import com.lauravelasquezcano.melichallenge.data.source.ItemsRepository
import com.lauravelasquezcano.melichallenge.utils.mockedItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SaveItemUseCaseTest {

    private var itemsRepository: ItemsRepository = mockk()

    private lateinit var saveItemUseCase: SaveItemUseCase

    @Before
    fun setUp() {
        saveItemUseCase = SaveItemUseCase(itemsRepository)
    }

    @After
    fun tearDown(){
        confirmVerified(itemsRepository)
    }

    @Test
    fun saveItem() = runBlocking {

        coEvery { itemsRepository.saveItem(mockedItem) } returns 3

        val result = saveItemUseCase.execute(mockedItem)

        assertEquals(3, result)
        coVerify(exactly = 1) { itemsRepository.saveItem(mockedItem) }
    }
}