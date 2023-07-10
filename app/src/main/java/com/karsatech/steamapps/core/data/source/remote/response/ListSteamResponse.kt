package com.karsatech.steamapps.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListSteamResponse(

    @field:SerializedName("specials")
    val specials: Specials,

) {
    data class Specials(

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: String,

        @field:SerializedName("items")
        val items: List<SteamResponse>
    )
}

