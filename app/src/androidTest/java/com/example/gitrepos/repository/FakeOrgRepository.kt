package com.example.gitrepos.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.gitrepos.models.Organization
import com.example.gitrepos.models.OrganizationsResponse
import com.example.gitrepos.models.RepositoriesResponse
import com.example.gitrepos.models.Repository
import com.example.gitrepos.util.Resource
import java.lang.Exception

class FakeOrgRepository : OrgRepositoryInterface {

    val allFakeOrgs : MutableState<List<Organization>> = mutableStateOf<List<Organization>>(emptyList<Organization>())
    val allFakeRepos : MutableState<List<Repository>> = mutableStateOf<List<Repository>>(emptyList())

    override suspend fun getAllOrgs(): OrganizationsResponse {
        return if(false) {
            throw Exception("Error")
        } else {
            OrganizationsResponse()
        }
    }

    override suspend fun getOrgsRepos(orgName: String?): RepositoriesResponse {
        return if(false) {
            throw Exception("Error")
        } else {
            RepositoriesResponse()
        }
    }

    override suspend fun getReposBySearch(login: String?): List<Repository> {
        return if(false) {
            throw Exception("Error")
        } else {
            RepositoriesResponse()
        }
    }
}