package com.example.gitrepos.repository

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import com.example.gitrepos.models.Organization
import com.example.gitrepos.models.OrganizationsResponse
import com.example.gitrepos.models.RepositoriesResponse
import com.example.gitrepos.util.Resource
import java.lang.Exception

class FakeOrgsRepository : OrgRepoInterface {

    private val orgsList = mutableListOf<Organization>()

    private val observableOrgsList = MutableLiveData<List<Organization>>(orgsList)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value : Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableOrgsList.postValue(orgsList)
    }

    override suspend fun getAllOrgs(): OrganizationsResponse {
        return if(shouldReturnNetworkError) {
            throw Exception("Error has occurred")
        } else {
            OrganizationsResponse()
        }
    }

    override suspend fun getOrgsRepos(orgName: String): RepositoriesResponse {
        return if(shouldReturnNetworkError) {
            throw Exception("Error has occurred")
        } else {
            RepositoriesResponse()
        }
    }


    override suspend fun getReposBySearch(loginName: String): RepositoriesResponse {
        return if(shouldReturnNetworkError) {
            throw Exception("Error has occurred")
        } else {
            RepositoriesResponse()
        }
    }

}
