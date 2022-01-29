package com.example.gitrepos.repository

import androidx.compose.runtime.MutableState
import com.example.gitrepos.models.Organization
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
) : OrgRepoInterface {

    override suspend fun getAllOrgs(): List<Organization> {
        return try {
            api.getAllOrgs()
        } catch (e:Exception) {
            throw Exception("Error has occurred")
        }
    }

    override suspend fun getOrgsRepos(orgName: String): List<Repository> {
        return try {
            api.getOrgRepos(orgName).sortedByDescending { it.stargazersCount }.slice(0..2)
        } catch (e:Exception) {
            throw Exception("Error has occurred")
        }
    }

    override suspend fun getReposBySearch(loginName: String): RepositoriesResponse {
        return try {
            api.getReposBySearch(loginName)
        } catch (e:Exception) {
            throw Exception("Error has occurred")
        }
    }
}