package com.karsatech.steamapps.core.di

import android.content.Context
import androidx.room.Room
import com.karsatech.steamapps.core.data.source.local.room.SteamDao
import com.karsatech.steamapps.core.data.source.local.room.SteamDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SteamDatabase = Room.databaseBuilder(
        context,
        SteamDatabase::class.java, "Steam.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideSteamDao(database: SteamDatabase) : SteamDao = database.steamDao()
}