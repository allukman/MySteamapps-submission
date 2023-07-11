package com.karsatech.steamapps.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steam")
data class SteamEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var steamId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "discounted")
    var discounted: Boolean,

    @ColumnInfo(name = "discount_percent")
    var discountPercent: Int,

    @ColumnInfo(name = "original_price")
    var originalPrice: Int,

    @ColumnInfo(name = "final_price")
    var finalPrice: Int,

    @ColumnInfo(name = "currency")
    var currency: String,

    @ColumnInfo(name = "large_capsule_image")
    var largeImage: String,

    @ColumnInfo(name = "small_capsule_image")
    var smallImage: String,

    @ColumnInfo(name = "windows_available")
    var windowsAvailable: Boolean,

    @ColumnInfo(name = "mac_available")
    var macAvailable: Boolean,

    @ColumnInfo(name = "linux_available")
    var linuxAvailable: Boolean,

    @ColumnInfo(name = "header_image")
    var headerImage: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)