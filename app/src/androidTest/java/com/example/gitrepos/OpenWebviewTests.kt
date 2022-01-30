package com.example.gitrepos

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.DriverAtoms.webClick
import androidx.test.espresso.web.webdriver.Locator
import com.example.gitrepos.repository.FakeOrgRepository
import com.example.gitrepos.ui.composables.RepoLinkDetailsScreen
import com.example.gitrepos.ui.composables.RepoListScreen
import com.example.gitrepos.ui.viewModels.RepoLinksViewModel
import com.example.gitrepos.ui.viewModels.RepoListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.regex.Matcher

class OpenWebviewTests {

    private lateinit var navController : NavHostController
    private lateinit var viewModel : RepoLinksViewModel

    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {

    }

    @Test
    fun detailsCardClickOpen() {
        composeTestRule.setContent {
            navController = rememberNavController()
            RepoLinkDetailsScreen(orgName = "errfree", navController = navController,
                viewModel = RepoLinksViewModel(FakeOrgRepository()), modifier = Modifier)
        }
        composeTestRule.onAllNodesWithContentDescription("Link Details Screen").assertAll(
            hasClickAction())
        composeTestRule.onAllNodesWithTag("Details Screen Card").assertAll(
            hasClickAction())
        onWebView().withElement(findElement(Locator.TAG_NAME, "Link Details Screen"))
            .forceJavascriptEnabled()
            .perform(webClick())
    }

}