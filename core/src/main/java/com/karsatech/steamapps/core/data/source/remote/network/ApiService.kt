package com.karsatech.steamapps.core.data.source.remote.network

import com.karsatech.steamapps.core.data.source.remote.response.DetailSteamResponse
import com.karsatech.steamapps.core.data.source.remote.response.ListSteamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/featuredcategories")
    suspend fun getList(): ListSteamResponse

    @GET("api/libraryappdetails/")
    suspend fun getDetailApps(@Query("appid")id: String): DetailSteamResponse
}
