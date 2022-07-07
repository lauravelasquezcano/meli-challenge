package com.lauravelasquezcano.melichallenge

import android.os.SystemClock
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lauravelasquezcano.melichallenge.app.ui.main.search.SearchFragment
import com.lauravelasquezcano.melichallenge.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun showAllComponents() {
        launchSearchFragment()
        SystemClock.sleep(1000)
        onView(withId(R.id.iv_mercadolibre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.et_search))
            .check(matches(isDisplayed()))
    }

    private fun launchSearchFragment() {
        launchFragmentInHiltContainer<SearchFragment> {
            navController.setGraph(R.navigation.navigation)
            navController.setCurrentDestination(R.id.fragment_search)
            this.viewLifecycleOwnerLiveData.observeForever { viewLifeCycleOwner ->
                if (viewLifeCycleOwner != null) {
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
    }
}