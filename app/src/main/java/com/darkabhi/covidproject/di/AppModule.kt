package com.darkabhi.covidproject.di

import com.darkabhi.covidproject.app.AppConfig
import com.darkabhi.covidproject.data.network.repository.ResourcesRepository
import com.darkabhi.covidproject.data.network.service.CovidApiService
import com.darkabhi.covidproject.data.network.service.NewsApiService
import com.darkabhi.covidproject.data.network.service.ResourcesApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResourcesRepository(
        resourcesApiService: ResourcesApiService
    ): ResourcesRepository = ResourcesRepository(resourcesApiService)

    @Provides
    @Singleton
    fun getCovidRetrofit(): CovidApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(AppConfig.COVID_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CovidApiService::class.java)
    }

    @Provides
    @Singleton
    fun getNewsRetrofit(): NewsApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(AppConfig.NEWS_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun getCovidResourcesRetrofit(): ResourcesApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(AppConfig.RELIEF_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ResourcesApiService::class.java)
    }
}
