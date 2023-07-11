package com.karsatech.steamapps.core.data.source.remote

import android.util.Log
import com.karsatech.steamapps.core.data.Resource
import com.karsatech.steamapps.core.data.source.remote.network.ApiResponse
import com.karsatech.steamapps.core.data.source.remote.network.ApiService
import com.karsatech.steamapps.core.data.source.remote.response.DetailSteamResponse
import com.karsatech.steamapps.core.data.source.remote.response.SteamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllSteam() : Flow<ApiResponse<List<SteamResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.specials.items
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.specials.items))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailApp(id: String) : Flow<com.karsatech.steamapps.core.data.Resource<DetailSteamResponse>> {
        return flow {
            emit(com.karsatech.steamapps.core.data.Resource.Loading())
            try {
                val response = apiService.getDetailApps(id)
                emit(com.karsatech.steamapps.core.data.Resource.Success(response))
            } catch (e: Exception) {
                emit(com.karsatech.steamapps.core.data.Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

