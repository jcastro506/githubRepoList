package com.example.gitrepos

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitrepos.remote.RepoApi
import com.example.gitrepos.repository.OrgRepoInterface
import com.example.gitrepos.repository.OrganizationRepository
import com.example.gitrepos.ui.composables.RepoCard
import com.example.gitrepos.ui.composables.RepoListScreen
import com.example.gitrepos.ui.viewModels.RepoListViewModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CardCLickTest {

    private lateinit var navController: TestNavHostController

    private lateinit var repoListViewModel : RepoListViewModel

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun cardClickAndScrollTest() {
        composeTestRule.setContent {
            MainActivity()
        }

        composeTestRule.onAllNodesWithContentDescription("All List Cards").assertAll(hasClickAction())
        composeTestRule.onAllNodesWithContentDescription("All List Cards").assertAll(
            hasScrollAction())
        composeTestRule.onAllNodesWithContentDescription("Details Card").assertAll(hasClickAction())
    }



}