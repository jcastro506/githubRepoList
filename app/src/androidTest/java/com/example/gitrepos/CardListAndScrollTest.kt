package com.example.gitrepos

import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.navigation.NavController
import com.example.gitrepos.ui.composables.RepoListScreen
import com.example.gitrepos.ui.viewModels.RepoListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardListAndScrollTest {


    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MainActivity()
        }
    }

    @Test
    fun testForClickAndScrollAction() {
        composeTestRule.onAllNodesWithContentDescription("List Screen Card").assertAll(
            hasClickAction())
        composeTestRule.onAllNodesWithContentDescription("List Screen Card").assertAll(
            hasScrollAction())
        composeTestRule.onAllNodesWithContentDescription("Details Screen Card").assertAll(
            hasClickAction())
    }


}