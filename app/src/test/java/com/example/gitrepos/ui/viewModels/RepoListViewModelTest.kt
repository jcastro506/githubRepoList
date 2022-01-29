package com.example.gitrepos.ui.viewModels

import android.provider.ContactsContract
import com.example.gitrepos.models.Organization
import com.example.gitrepos.repository.FakeOrgsRepository
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class RepoListViewModelTest {

    private lateinit var viewModel : RepoListViewModel


    @Before
    fun setUp() {
        viewModel = RepoListViewModel(FakeOrgsRepository())
    }


    @Test
    fun firstTest() {
        val value = viewModel.allOrgs

       assertThat(value).isEqualTo(mutableListOf<Organization>())
    }



}