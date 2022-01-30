package com.example.gitrepos

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gitrepos.repository.FakeOrgRepository
import com.example.gitrepos.ui.composables.RepoListScreen
import com.example.gitrepos.ui.viewModels.RepoListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.regex.Matcher


class SearchTests {

    private lateinit var navController : NavHostController
    private lateinit var viewModel : RepoListViewModel

    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {

    }

    @Test
    fun searchBarTests() {
        val dummyText = "Dummy Text"
        composeTestRule.setContent {
            navController = rememberNavController()
            RepoListScreen(navController = navController, viewModel = RepoListViewModel(FakeOrgRepository()),
                modifier = Modifier)
        }

        composeTestRule.onAllNodesWithContentDescription("Search Bar").fetchSemanticsNodes(true)
        composeTestRule.onAllNodesWithContentDescription("Search Bar").assertAll(hasClickAction())
        composeTestRule.onAllNodesWithContentDescription("Search Bar").assertAll(hasSetTextAction())
    }

}