package com.karsatech.steamapps.di

import com.karsatech.steamapps.core.domain.usecase.SteamUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun steamUseCase(): SteamUseCase
}