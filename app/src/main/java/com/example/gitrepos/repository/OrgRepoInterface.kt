package com.example.gitrepos.repository

import com.example.gitrepos.models.Organization
import com.example.gitrepos.models.OrganizationsResponse
import com.example.gitrepos.models.RepositoriesResponse
import com.example.gitrepos.models.Repository
import com.example.gitrepos.util.Resource
import dagger.Provides
import dagger.hilt.android.scopes.ActivityScoped


interface OrgRepoInterface {

    suspend fun getAllOrgs() : List<Organization>

    suspend fun getOrgsRepos(orgName: String) : List<Repository>

    suspend fun getReposBySearch(loginName: String) : RepositoriesResponse
}