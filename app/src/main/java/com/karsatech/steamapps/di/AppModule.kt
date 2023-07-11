package com.karsatech.steamapps.di

import com.karsatech.steamapps.core.domain.usecase.SteamInteractor
import com.karsatech.steamapps.core.domain.usecase.SteamUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideSteamUseCase(steamInteractor: SteamInteractor): SteamUseCase

}