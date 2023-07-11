package com.karsatech.steamapps.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SteamResponse(
    @field:SerializedName("id")
    var steamId: Int,

    @field:SerializedName("name")
    var name: String,

    @field:SerializedName("discounted")
    var discounted: Boolean,

    @field:SerializedName("discount_percent")
    var discountPercent: Int,

    @field:SerializedName("original_price")
    var originalPrice: Int,

    @field:SerializedName("final_price")
    var finalPrice: Int,

    @field:SerializedName("currency")
    var currency: String,

    @field:SerializedName("large_capsule_image")
    var largeImage: String,

    @field:SerializedName("small_capsule_image")
    var smallImage: String,

    @field:SerializedName("windows_available")
    var windowsAvailable: Boolean,

    @field:SerializedName("mac_available")
    var macAvailable: Boolean,

    @field:SerializedName("linux_available")
    var linuxAvailable: Boolean,

    @field:SerializedName("header_image")
    var headerImage: String,

    @field:SerializedName("isFavorite")
    var isFavorite: Boolean = false
)
