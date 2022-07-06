package com.lauravelasquezcano.melichallenge.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lauravelasquezcano.melichallenge.app.ui.main.details.DetailViewModel
import com.lauravelasquezcano.melichallenge.data.model.GetItemState
import com.lauravelasquezcano.melichallenge.usecases.GetItemUseCase
import com.lauravelasquezcano.melichallenge.utils.mockedDbItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private var getItemUseCase: GetItemUseCase = mockk()

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel(getItemUseCase)
    }

    @After
    fun tearDown() {
        confirmVerified(getItemUseCase)
    }

    @Test
    fun getItemByIdSuccess() = runTest {
        coEvery { getItemUseCase.execute("asdf") } returns mockedDbItem

        viewModel.getItemById("asdf")

        coVerify(exactly = 1) { getItemUseCase.execute("asdf") }
        assertEquals(GetItemState.Success(mockedDbItem), viewModel.getItemState.value)
    }

    @Test
    fun getItemByIdFailure() = runTest {
        coEvery { getItemUseCase.execute("asdf") } returns null

        viewModel.getItemById("asdf")

        coVerify(exactly = 1) { getItemUseCase.execute("asdf") }
        assertEquals(GetItemState.Failure, viewModel.getItemState.value)
    }
}