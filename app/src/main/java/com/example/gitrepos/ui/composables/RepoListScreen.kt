package com.example.gitrepos.ui.composables

import android.service.autofill.OnClickAction
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.gitrepos.models.Repository
import com.example.gitrepos.ui.viewModels.RepoLinksViewModel
import com.example.gitrepos.ui.viewModels.RepoListViewModel


@Composable
fun RepoListScreen(
    navController: NavController,
    viewModel: RepoListViewModel
) {
    val allOrgs by remember { viewModel.allOrgs }
    val isSearching by remember { viewModel.isSearching }
    androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
        Column() {
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                viewModel.searchOrgList(it)
            }
            LazyColumn(){
                items(allOrgs) { org ->
                    RepoCard(orgName = org.login, orgImage = org.avatarUrl, navController)
                }
            }
        }
    }
}


@Composable
fun RepoCard(orgName : String, orgImage : String, navController: NavController) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)
        .clickable { navController.navigate("repoDetailsScreen/${orgName}") },
        elevation = 8.dp,
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Row(modifier = Modifier.wrapContentSize(align = Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
            ) {
            OrgPicture(orgImage)
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                OrgName(orgName)
            }
        }
    }
}


@Composable
fun OrgPicture(imageUrl : String) {
    Image(painter = rememberImagePainter(imageUrl), contentDescription = "avatar",
        modifier = Modifier
            .size(128.dp)
            .padding(8.dp))
}


@Composable
fun OrgName(orgName : String) {
        Text(text = orgName)
}


@Composable
fun SearchedReposScreen(navController: NavController,
                        viewModel: RepoListViewModel,
                        repo: Repository
) {
    Card(modifier = Modifier) {
        Text(text = repo.name)
    }
}


@Composable
fun SearchBar(modifier: Modifier=Modifier,
              onSearch: (String) -> Unit = {})
{
    var text by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.padding(16.dp)) {
        BasicTextField(value = text, onValueChange = {
            text = it
            onSearch(it)
        },
        maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .background(Color.White, CircleShape)
            )
    }
}