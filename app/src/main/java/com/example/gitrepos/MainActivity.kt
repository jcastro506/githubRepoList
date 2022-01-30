package com.example.gitrepos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gitrepos.ui.composables.RepoLinkDetailsScreen
import com.example.gitrepos.ui.composables.RepoListScreen
import com.example.gitrepos.ui.composables.WebPageScreen
import com.example.gitrepos.ui.theme.GitReposTheme
import com.example.gitrepos.ui.viewModels.RepoLinksViewModel
import com.example.gitrepos.ui.viewModels.RepoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel:RepoListViewModel by viewModels()
            val linksViewModel : RepoLinksViewModel by viewModels()
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "repos") {
                composable("repos") {
                    RepoListScreen(navController = navController, viewModel = viewModel,
                        modifier = Modifier.semantics { contentDescription = "Repo List Screen" })
                }
                composable("repoDetailsScreen/{orgName}", arguments = listOf(
                    navArgument("orgName"){
                        type = NavType.StringType
                    }
                )) {
                    val orgName = remember {
                        it.arguments?.getString("orgName")
                    }
                    RepoLinkDetailsScreen(orgName = orgName, navController = navController, viewModel = linksViewModel,
                        modifier = Modifier.semantics { testTag = "Link Details Screen" })
                }
                composable("webViewScreen/{repoUrl}", arguments = listOf(
                    navArgument("repoUrl") {
                        type = NavType.StringType
                    }
                )) {
                    val repoUrl = remember { it.arguments?.getString("repoUrl") }
                    if (repoUrl != null) {
                        WebPageScreen(urlToRender = repoUrl)
                    }
                }
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GitReposTheme {

    }
}