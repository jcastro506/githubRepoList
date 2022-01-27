package com.example.gitrepos.di

import com.example.gitrepos.remote.RepoApi
import com.example.gitrepos.repository.OrganizationRepository
import com.example.gitrepos.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.internal.PrepareOp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOrgRepository(
        api : RepoApi
    ) = OrganizationRepository(api)


    @Singleton
    @Provides
    fun provideRepoApi() : RepoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RepoApi::class.java);
    }
}