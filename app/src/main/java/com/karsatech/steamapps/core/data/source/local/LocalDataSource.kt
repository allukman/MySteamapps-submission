package com.karsatech.steamapps.core.data.source.local

import com.karsatech.steamapps.core.data.source.local.entity.SteamEntity
import com.karsatech.steamapps.core.data.source.local.room.SteamDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val steamDao: SteamDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(steamDao: SteamDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(steamDao)
            }
    }

    fun getAllSteam(): Flow<List<SteamEntity>> = steamDao.getAllSteam()

    fun getFavoriteSteam(): Flow<List<SteamEntity>> = steamDao.getFavoriteSteam()

    suspend fun insertSteam(steamList: List<SteamEntity>) = steamDao.insertSteam(steamList)

    fun setFavoriteSteam(steam: SteamEntity, newState: Boolean) {
        steam.isFavorite = newState
        steamDao.updateFavoriteSteam(steam)
    }
}