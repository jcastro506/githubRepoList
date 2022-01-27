package com.example.gitrepos.remote

import androidx.compose.runtime.MutableState
import com.example.gitrepos.models.OrganizationsResponse
import com.example.gitrepos.models.RepositoriesResponse
import com.example.gitrepos.models.Repository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoApi {

    @GET("organizations")
    suspend fun getAllOrgs() : OrganizationsResponse


    @GET("orgs/{orgName}/repos")
    suspend fun getOrgRepos(
        @Path("orgName") orgName : String?
    ) : RepositoriesResponse


    @GET("search/repositories/{loginName}&sort=stars&order=desc")
    suspend fun getReposBySearch(
        @Query("q")
        loginName : String?
    ) : RepositoriesResponse

}