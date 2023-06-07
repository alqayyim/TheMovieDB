package com.asad.themoviedb.utils


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.asad.themoviedb.utils.CoroutineTestRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

abstract class BaseViewModelTest {
    @get:Rule
    open val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    open val coroutineTestRule = CoroutineTestRule()

    @Before
    open fun start() {
        MockKAnnotations.init(this)
    }
}