package com.example.gitrepos.ui.composables

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gitrepos.ui.viewModels.RepoLinksViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter

@Composable
fun RepoLinkDetailsScreen(
    orgName: String?,
    navController: NavController,
    viewModel: RepoLinksViewModel

) {
    viewModel.getRepoList(orgName)
    var repos = viewModel.repoList.value
    LazyColumn() {
      items(repos) { repo ->
          RepoCard(
              repoName = repo.name, repoImage = repo.owner.avatarUrl,
              repoUrl = repo.htmlUrl, navController = navController){

          }
      }
    }
}


@Composable
fun RepoCard(
    repoName: String, repoImage: String,
    repoUrl: String, navController: NavController, onClickAction: () -> Unit
) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)
        .clickable { navController.navigate("webViewScreen/${repoUrl}") }
        ,
        elevation = 8.dp,
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Row(modifier = Modifier.wrapContentSize(align = Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            RepoPicture(repoImage)
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                RepoName(repoName)
            }
        }
    }
}


@Composable
fun RepoPicture(imageUrl : String) {
    Image(painter = rememberImagePainter(imageUrl), contentDescription = "avatar",
        modifier = Modifier
            .size(128.dp)
            .padding(8.dp))
}


@Composable
fun RepoName(orgName : String) {
    Text(text = orgName)
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebPageScreen(urlToRender: String) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(urlToRender)
        }
    }, update = {
        it.loadUrl(urlToRender)
    })
}