package com.example.gitrepos

import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import com.example.gitrepos.repository.FakeOrgRepository
import com.example.gitrepos.ui.composables.RepoListScreen
import com.example.gitrepos.ui.viewModels.RepoLinksViewModel
import com.example.gitrepos.ui.viewModels.RepoListViewModel
import junit.framework.Assert.fail
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class NavigationTests {

    private lateinit var navController : NavHostController
    private lateinit var viewModel : RepoListViewModel


    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {

    }


    @Test
    fun clickOrgListCard() {
        composeTestRule.setContent {
            navController = rememberNavController()
            RepoListScreen(navController = navController, viewModel = RepoListViewModel(FakeOrgRepository()), modifier = Modifier)
        }
        composeTestRule.onAllNodesWithContentDescription("List Screen Card").get(0).performClick()
        composeTestRule.onAllNodesWithContentDescription("Details Screen Card").assertAll(
            hasClickAction())

        Espresso.pressBack()

        composeTestRule.onAllNodesWithContentDescription("List Screen Card").get(0).assertExists()
    }

}