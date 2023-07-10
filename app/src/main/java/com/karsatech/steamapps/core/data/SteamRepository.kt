package com.karsatech.steamapps.core.data

import com.karsatech.steamapps.core.data.source.local.LocalDataSource
import com.karsatech.steamapps.core.data.source.remote.RemoteDataSource
import com.karsatech.steamapps.core.data.source.remote.network.ApiResponse
import com.karsatech.steamapps.core.data.source.remote.response.DetailSteamResponse
import com.karsatech.steamapps.core.data.source.remote.response.SteamResponse
import com.karsatech.steamapps.core.domain.model.Steam
import com.karsatech.steamapps.core.domain.repository.ISteamRepository
import com.karsatech.steamapps.core.utils.AppExecutors
import com.karsatech.steamapps.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SteamRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISteamRepository {

    companion object {
        @Volatile
        private var instance: SteamRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): SteamRepository =
            instance ?: synchronized(this) {
                instance ?: SteamRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllSteam(): Flow<Resource<List<Steam>>> =
        object : NetworkBoundResource<List<Steam>, List<SteamResponse>>() {
            override fun loadFromDB(): Flow<List<Steam>> {
                return localDataSource.getAllSteam().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<SteamResponse>>> =
                remoteDataSource.getAllSteam()

            override suspend fun saveCallResult(data: List<SteamResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSteam(tourismList)
            }

            override fun shouldFetch(data: List<Steam>?): Boolean =
                data.isNullOrEmpty()
        }.asFlow()

    override fun getFavoriteSteam(): Flow<List<Steam>> {
        return localDataSource.getFavoriteSteam().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteSteam(steam: Steam, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(steam)
        appExecutors.diskIO().execute { localDataSource.setFavoriteSteam(tourismEntity, state) }
    }

    override fun getDetailSteam(id: String): Flow<Resource<DetailSteamResponse>> {
        return remoteDataSource.getDetailApp(id)
    }
}

