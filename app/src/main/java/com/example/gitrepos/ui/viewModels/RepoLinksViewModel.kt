package com.example.gitrepos.ui.viewModels

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitrepos.models.RepositoriesResponse
import com.example.gitrepos.models.Repository
import com.example.gitrepos.repository.OrganizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RepoLinksViewModel @Inject constructor(
    private val repository: OrganizationRepository
) : ViewModel() {

    var repoList : MutableState<List<Repository>> = mutableStateOf<List<Repository>>(emptyList())
    var orgName : MutableState<String> = mutableStateOf("")


    fun getRepoList(orgName : String?) = viewModelScope.launch {
        val response = repository.getOrgRepos(orgName).sortedByDescending { it.stargazersCount }.slice(0..2)
        repoList.value = response
    }

    fun getReposBySearch(loginName : String?) = viewModelScope.launch {
        val response = repository.getReposBySearch(loginName = loginName)
            .sortedByDescending{ it.stargazersCount }.slice(0..2)

        repoList.value = response
    }

}
