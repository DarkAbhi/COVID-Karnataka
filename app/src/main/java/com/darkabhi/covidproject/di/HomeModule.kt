package com.darkabhi.covidproject.di

import com.darkabhi.covidproject.data.network.service.CovidApiService
import com.darkabhi.covidproject.data.network.service.NewsApiService
import com.darkabhi.covidproject.home.data.network.repository.CovidRepositoryImpl
import com.darkabhi.covidproject.home.data.network.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 6/1/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideCovidRepository(covidApiService: CovidApiService) =
        CovidRepositoryImpl(covidApiService)

    @Provides
    @Singleton
    fun provideNewsRepository(newsApiService: NewsApiService) = NewsRepositoryImpl(newsApiService)
}
