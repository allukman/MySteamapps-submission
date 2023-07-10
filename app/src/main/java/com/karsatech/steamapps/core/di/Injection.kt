package com.karsatech.steamapps.core.di

import android.content.Context
import com.karsatech.steamapps.core.data.SteamRepository
import com.karsatech.steamapps.core.data.source.local.LocalDataSource
import com.karsatech.steamapps.core.data.source.local.room.TourismDatabase
import com.karsatech.steamapps.core.data.source.remote.RemoteDataSource
import com.karsatech.steamapps.core.data.source.remote.network.ApiConfig
import com.karsatech.steamapps.core.domain.repository.ISteamRepository
import com.karsatech.steamapps.core.domain.usecase.SteamInteractor
import com.karsatech.steamapps.core.domain.usecase.SteamUseCase
import com.karsatech.steamapps.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): ISteamRepository {
        val database = TourismDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutors()

        return SteamRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): SteamUseCase {
        val repository = provideRepository(context)
        return SteamInteractor(repository)
    }
}
