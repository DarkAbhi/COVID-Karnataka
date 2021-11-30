package com.darkabhi.covidproject.di

import android.app.Application
import androidx.room.Room
import com.darkabhi.covidproject.data.room.CovidAppDatabase
import com.darkabhi.covidproject.home.state.data.local.dao.DistrictDetailsDao
import com.darkabhi.covidproject.home.state.data.local.dao.StateDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): CovidAppDatabase {
        return Room.databaseBuilder(
            application,
            CovidAppDatabase::class.java,
            "covid_app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDistrictDetailsDao(covidAppDatabase: CovidAppDatabase): DistrictDetailsDao =
        covidAppDatabase.districtDetailsDao()

    @Provides
    @Singleton
    fun provideStateDetailsDao(covidAppDatabase: CovidAppDatabase): StateDetailDao =
        covidAppDatabase.stateDetailsDao()
}
