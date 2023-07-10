package com.karsatech.steamapps.core.data.source.local

import com.karsatech.steamapps.core.data.source.local.entity.SteamEntity
import com.karsatech.steamapps.core.data.source.local.room.TourismDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val tourismDao: TourismDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: TourismDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }

    fun getAllSteam(): Flow<List<SteamEntity>> = tourismDao.getAllSteam()

    fun getFavoriteSteam(): Flow<List<SteamEntity>> = tourismDao.getFavoriteSteam()

    suspend fun insertSteam(steamList: List<SteamEntity>) = tourismDao.insertSteam(steamList)

    fun setFavoriteSteam(steam: SteamEntity, newState: Boolean) {
        steam.isFavorite = newState
        tourismDao.updateFavoriteSteam(steam)
    }
}