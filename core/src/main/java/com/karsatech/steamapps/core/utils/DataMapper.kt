package com.karsatech.steamapps.core.utils

import com.karsatech.steamapps.core.data.source.local.entity.SteamEntity
import com.karsatech.steamapps.core.data.source.remote.response.SteamResponse
import com.karsatech.steamapps.core.domain.model.Steam

object DataMapper {
    fun mapResponsesToEntities(input: List<SteamResponse>): List<SteamEntity> {
        val steamList = ArrayList<SteamEntity>()
        input.map {
            val steam = SteamEntity(
                steamId = it.steamId,
                name = it.name,
                discounted = it.discounted,
                discountPercent = it.discountPercent,
                originalPrice = it.originalPrice,
                finalPrice = it.finalPrice,
                currency = it.currency,
                largeImage = it.largeImage,
                smallImage = it.smallImage,
                windowsAvailable = it.windowsAvailable,
                macAvailable = it.macAvailable,
                linuxAvailable = it.linuxAvailable,
                headerImage = it.headerImage,
                isFavorite = it.isFavorite
            )
            steamList.add(steam)
        }
        return steamList
    }

    fun mapEntitiesToDomain(input: List<SteamEntity>): List<Steam> =
        input.map {
            Steam(
                steamId = it.steamId,
                name = it.name,
                discounted = it.discounted,
                discountPercent = it.discountPercent,
                originalPrice = it.originalPrice,
                finalPrice = it.finalPrice,
                currency = it.currency,
                largeImage = it.largeImage,
                smallImage = it.smallImage,
                windowsAvailable = it.windowsAvailable,
                macAvailable = it.macAvailable,
                linuxAvailable = it.linuxAvailable,
                headerImage = it.headerImage,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Steam) = SteamEntity(
        steamId = input.steamId,
        name = input.name,
        discounted = input.discounted,
        discountPercent = input.discountPercent,
        originalPrice = input.originalPrice,
        finalPrice = input.finalPrice,
        currency = input.currency,
        largeImage = input.largeImage,
        smallImage = input.smallImage,
        windowsAvailable = input.windowsAvailable,
        macAvailable = input.macAvailable,
        linuxAvailable = input.linuxAvailable,
        headerImage = input.headerImage,
        isFavorite = input.isFavorite
    )
}