package com.karsatech.steamapps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Steam(
    val steamId: Int,
    val name: String,
    val discounted: Boolean,
    val discountPercent: Int,
    val originalPrice: Int,
    val finalPrice: Int,
    val currency: String,
    val largeImage: String,
    val smallImage: String,
    val windowsAvailable: Boolean,
    val macAvailable: Boolean,
    val linuxAvailable: Boolean,
    val headerImage: String,
    val isFavorite: Boolean
) : Parcelable