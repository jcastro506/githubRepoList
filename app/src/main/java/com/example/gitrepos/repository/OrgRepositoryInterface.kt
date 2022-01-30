package com.example.gitrepos.repository

import com.example.gitrepos.models.OrganizationsResponse
import com.example.gitrepos.models.RepositoriesResponse
import com.example.gitrepos.models.Repository

interface OrgRepositoryInterface {

    suspend fun getAllOrgs() : OrganizationsResponse

    suspend fun getOrgsRepos(orgName: String?) : RepositoriesResponse

    suspend fun getReposBySearch(loginName : String?) : List<Repository>

}