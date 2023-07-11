package com.karsatech.steamapps.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.karsatech.steamapps.core.data.source.local.entity.SteamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SteamDao {

    @Query("SELECT * FROM steam")
    fun getAllSteam(): Flow<List<SteamEntity>>

    @Query("SELECT * FROM steam where isFavorite = 1")
    fun getFavoriteSteam(): Flow<List<SteamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSteam(tourism: List<SteamEntity>)

    @Update
    fun updateFavoriteSteam(tourism: SteamEntity)
}
