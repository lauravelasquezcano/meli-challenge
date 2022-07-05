package com.lauravelasquezcano.melichallenge.repository

import com.lauravelasquezcano.melichallenge.data.remote.SearchApi
import com.lauravelasquezcano.melichallenge.data.repository.ItemsRepositoryImpl
import com.lauravelasquezcano.melichallenge.data.source.ItemsRepository
import com.lauravelasquezcano.melichallenge.data.source.LocalDataSource
import com.lauravelasquezcano.melichallenge.domain.ResultWrapper
import com.lauravelasquezcano.melichallenge.domain.SearchResponse
import com.lauravelasquezcano.melichallenge.utils.mockedDbItem
import com.lauravelasquezcano.melichallenge.utils.mockedItem
import com.lauravelasquezcano.melichallenge.utils.mockedSearchResponse
import io.mockk.*
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ItemsRepositoryImplTest {

    private var searchApi: SearchApi = mockk()

    private var localDataSource: LocalDataSource = mockk()

    private lateinit var itemsRepository: ItemsRepository

    @Before
    fun setUp() {
        itemsRepository = ItemsRepositoryImpl(
            searchApi,
            localDataSource
        )
    }

    @After
    fun tearDown() {
        confirmVerified(searchApi, localDataSource)
    }

    @Test
    fun searchItemsSuccess() = runBlocking {

        val response: Response<SearchResponse> = Response.success(mockedSearchResponse)

        coEvery { searchApi.getAllItems("motorola") } returns response

        val result = itemsRepository.searchItems("motorola") as ResultWrapper.Success

        coVerify(exactly = 1) {
            searchApi.getAllItems("motorola")
        }
        assertEquals(mockedSearchResponse, result.data)
    }

    @Test
    fun searchItemsFailure() = runBlocking {

        val searchResponse: Response<SearchResponse> =
            Response.error(
                422,
                "The given data was invalid".toResponseBody("application/json".toMediaTypeOrNull())
            )

        coEvery { searchApi.getAllItems("motorola") } returns searchResponse

        val result = itemsRepository.searchItems("motorola") as ResultWrapper.GenericError

        coVerify(exactly = 1) {
            searchApi.getAllItems("motorola")
        }
        assertEquals(422, result.code)
    }

    @Test
    fun getItemById() = runBlocking {

        every { localDataSource.getItemById("asdf") } returns mockedDbItem

        val item = itemsRepository.getItemById("asdf")

        verify(exactly = 1) { localDataSource.getItemById("asdf") }
        assertEquals(mockedDbItem, item)
    }

    @Test
    fun saveItem() = runBlocking {

        every { localDataSource.saveItem(mockedDbItem) } returns 4L

        val id = itemsRepository.saveItem(mockedItem)

        verify(exactly = 1) { localDataSource.saveItem(mockedDbItem) }
        assertEquals(4L, id)
    }
}