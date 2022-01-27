package com.example.gitrepos.ui.viewModels

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitrepos.models.Organization
import com.example.gitrepos.models.OrganizationsResponse
import com.example.gitrepos.models.RepositoriesResponse
import com.example.gitrepos.models.Repository
import com.example.gitrepos.repository.OrganizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RepoListViewModel @Inject constructor(
   var repository: OrganizationRepository
) : ViewModel(){

    val allOrgs : MutableState<List<Organization>> = mutableStateOf<List<Organization>>(emptyList<Organization>())
    val searchedRepos : MutableState<List<Repository>> = mutableStateOf(emptyList())
    private var cachedList = emptyList<Organization>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)


    init {
        viewModelScope.launch {
            allOrgs.value = getAllOrgs()
        }
    }

    fun searchOrgList(query : String) {
        val listToSearch = if(isSearchStarting) {
            allOrgs.value
        } else {
            cachedList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                allOrgs.value = cachedList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.login.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) {
                cachedList = allOrgs.value
                isSearchStarting = false
            }
            allOrgs.value = results
            isSearching.value = true
        }
    }


    private suspend fun getAllOrgs() : OrganizationsResponse {
        return repository.getAllOrgs()
    }


    fun getReposBySearch(loginName : String) = viewModelScope.launch {
        val response = repository.getReposBySearch(loginName = loginName)
        searchedRepos.value = response
    }

}