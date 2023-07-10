package com.karsatech.steamapps.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailSteamResponse(

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("appid")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("strFullDescription")
    val description: String,

    @field:SerializedName("strSnippet")
    val snippet: String

)