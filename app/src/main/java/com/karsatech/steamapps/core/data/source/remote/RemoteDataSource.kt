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

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

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

    fun getDetailApp(id: String) : Flow<Resource<DetailSteamResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getDetailApps(id)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

