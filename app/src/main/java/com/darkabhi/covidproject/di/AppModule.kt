package com.darkabhi.covidproject.di

import com.darkabhi.covidproject.BuildConfig
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val SOCKET_TIMEOUT = 10L

    @Provides
    @Singleton
    fun provideResourcesRepository(
        resourcesApiService: ResourcesApiService
    ): ResourcesRepository = ResourcesRepository(resourcesApiService)

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .connectTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    } else OkHttpClient.Builder()
        .connectTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun getCovidRetrofit(okHttpClient: OkHttpClient): CovidApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(AppConfig.COVID_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(CovidApiService::class.java)
    }

    @Provides
    @Singleton
    fun getNewsRetrofit(okHttpClient: OkHttpClient): NewsApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(AppConfig.NEWS_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
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
