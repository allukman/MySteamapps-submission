package com.karsatech.steamapps.core.data.source.local

import com.karsatech.steamapps.core.data.source.local.entity.SteamEntity
import com.karsatech.steamapps.core.data.source.local.room.SteamDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val steamDao: SteamDao) {

    fun getAllSteam(): Flow<List<SteamEntity>> = steamDao.getAllSteam()

    fun getFavoriteSteam(): Flow<List<SteamEntity>> = steamDao.getFavoriteSteam()

    suspend fun insertSteam(steamList: List<SteamEntity>) = steamDao.insertSteam(steamList)

    fun setFavoriteSteam(steam: SteamEntity, newState: Boolean) {
        steam.isFavorite = newState
        steamDao.updateFavoriteSteam(steam)
    }
}