package com.karsatech.steamapps.core.di

import com.karsatech.steamapps.core.data.SteamRepository
import com.karsatech.steamapps.core.domain.repository.ISteamRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(steamRepository: SteamRepository):ISteamRepository

}