package com.lauravelasquezcano.melichallenge.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lauravelasquezcano.melichallenge.app.ui.main.results.ResultsViewModel
import com.lauravelasquezcano.melichallenge.data.model.GetItemState
import com.lauravelasquezcano.melichallenge.data.model.SaveItemState
import com.lauravelasquezcano.melichallenge.data.model.SearchItemState
import com.lauravelasquezcano.melichallenge.domain.ResultWrapper
import com.lauravelasquezcano.melichallenge.domain.SearchResponse
import com.lauravelasquezcano.melichallenge.usecases.SaveItemUseCase
import com.lauravelasquezcano.melichallenge.usecases.SearchItemsUseCase
import com.lauravelasquezcano.melichallenge.utils.mockedDbItem
import com.lauravelasquezcano.melichallenge.utils.mockedItem
import com.lauravelasquezcano.melichallenge.utils.mockedSearchResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.rules.TestRule

class ResultsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private var searchItemsUseCase: SearchItemsUseCase = mockk()
    private var saveItemUseCase: SaveItemUseCase = mockk()

    private lateinit var viewModel: ResultsViewModel

    @Before
    fun setUp() {
        viewModel = ResultsViewModel(searchItemsUseCase, saveItemUseCase)
    }

    @After
    fun tearDown() {
        confirmVerified(searchItemsUseCase, saveItemUseCase)
    }

    @Test
    fun searchItemsSuccess() {

        val itemsResult = ResultWrapper.Success(mockedSearchResponse)

        coEvery { searchItemsUseCase.execute("asdf") } returns itemsResult

        viewModel.searchItems("asdf")

        coVerify(exactly = 1) { searchItemsUseCase.execute("asdf") }
        assertEquals(
            SearchItemState.Success(mockedSearchResponse.results),
            viewModel.searchItemState.value
        )
    }

    @Test
    fun searchItemsFailure() {

        val itemsResult: ResultWrapper.GenericError<SearchResponse> =
            ResultWrapper.GenericError(422, "ERROR")

        coEvery { searchItemsUseCase.execute("asdf") } returns itemsResult

        viewModel.searchItems("asdf")

        coVerify(exactly = 1) { searchItemsUseCase.execute("asdf") }
        assertEquals(
            SearchItemState.Failure("ERROR"),
            viewModel.searchItemState.value
        )
    }

    @Test
    fun saveItemSuccess() {
        coEvery { saveItemUseCase.execute(mockedItem) } returns 4

        viewModel.saveItem(mockedItem)
        coVerify(exactly = 1) { saveItemUseCase.execute(mockedItem) }
        assertEquals(SaveItemState.Success, viewModel.saveItemState.value)
    }

    @Test
    fun saveItemFailure() {
        coEvery { saveItemUseCase.execute(mockedItem) } returns -1L

        viewModel.saveItem(mockedItem)
        coVerify(exactly = 1) { saveItemUseCase.execute(mockedItem) }
        assertEquals(SaveItemState.Failure, viewModel.saveItemState.value)
    }
}