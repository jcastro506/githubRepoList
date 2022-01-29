package com.example.gitrepos.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitrepos.models.Organization
import com.example.gitrepos.models.OrganizationsResponse
import com.example.gitrepos.models.Repository
import com.example.gitrepos.repository.OrgRepoInterface
import com.example.gitrepos.repository.OrganizationRepository
import com.example.gitrepos.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RepoListViewModel @Inject constructor(
   var repository: OrgRepoInterface
) : ViewModel(){

    //val allOrgs : MutableState<Resource<OrganizationsResponse>> = mutableStateOf()
    val allOrgs : MutableState<List<Organization>> = mutableStateOf(emptyList())

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

    fun resetOrgList() = viewModelScope.launch {
        allOrgs.value = getAllOrgs()
    }

    private suspend fun getAllOrgs() : List<Organization> {
        return repository.getAllOrgs()
    }


    fun getReposBySearch(loginName : String) = viewModelScope.launch {
        val response = repository.getReposBySearch(loginName = loginName)
        searchedRepos.value = response
    }

}