package com.lauravelasquezcano.melichallenge.usecases

import com.lauravelasquezcano.melichallenge.data.source.ItemsRepository
import com.lauravelasquezcano.melichallenge.domain.ResultWrapper
import com.lauravelasquezcano.melichallenge.domain.SearchResponse
import com.lauravelasquezcano.melichallenge.utils.mockedSearchResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchItemsUseCaseTest {

    private var itemRepository: ItemsRepository = mockk()

    private lateinit var searchItemsUseCase: SearchItemsUseCase

    @Before
    fun setUp() {
        searchItemsUseCase = SearchItemsUseCase(itemRepository)
    }

    @After
    fun tearDown() {
        confirmVerified(itemRepository)
    }

    @Test
    fun searchItemSuccess() = runBlocking {

        coEvery {
            itemRepository.searchItems("motorola")
        } returns ResultWrapper.Success(mockedSearchResponse)

        val result = searchItemsUseCase.execute("motorola") as ResultWrapper.Success

        assertEquals(mockedSearchResponse, result.data)
        coVerify(exactly = 1) { itemRepository.searchItems("motorola") }
    }

    @Test
    fun searchItemFailure() = runBlocking {

        val response: ResultWrapper.GenericError<SearchResponse> =
            ResultWrapper.GenericError(400, "ERROR")

        coEvery { itemRepository.searchItems("motorola") } returns response

        val result = searchItemsUseCase.execute("motorola") as ResultWrapper.GenericError

        assertEquals(400, result.code)
        assertEquals("ERROR", result.message)
        coVerify(exactly = 1) { itemRepository.searchItems("motorola") }
    }
}