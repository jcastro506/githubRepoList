package com.example.gitrepos.repository

import androidx.compose.runtime.MutableState
import com.example.gitrepos.models.OrganizationsResponse
import com.example.gitrepos.models.RepositoriesResponse
import com.example.gitrepos.models.Repository
import com.example.gitrepos.remote.RepoApi
import com.example.gitrepos.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log


@ActivityScoped
class OrganizationRepository @Inject constructor(
    private val api: RepoApi
) {

    suspend fun getAllOrgs() : OrganizationsResponse {
        return api.getAllOrgs()
    }

    suspend fun getOrgRepos(orgName : String?) : RepositoriesResponse {
        return api.getOrgRepos(orgName = orgName)
    }

    suspend fun getReposBySearch(loginName : String?) : List<Repository> {
        return api.getReposBySearch(loginName = loginName)
            .sortedByDescending{ it.stargazersCount }.slice(0..2)
    }

}