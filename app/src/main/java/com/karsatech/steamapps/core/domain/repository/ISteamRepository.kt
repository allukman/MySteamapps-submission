package com.karsatech.steamapps.core.domain.repository

import com.karsatech.steamapps.core.data.Resource
import com.karsatech.steamapps.core.data.source.remote.response.DetailSteamResponse
import com.karsatech.steamapps.core.domain.model.Steam
import kotlinx.coroutines.flow.Flow

interface ISteamRepository {

    fun getAllSteam(): Flow<Resource<List<Steam>>>

    fun getFavoriteSteam(): Flow<List<Steam>>

    fun setFavoriteSteam(steam: Steam, state: Boolean)

    fun getDetailSteam(id: String): Flow<Resource<DetailSteamResponse>>

}
