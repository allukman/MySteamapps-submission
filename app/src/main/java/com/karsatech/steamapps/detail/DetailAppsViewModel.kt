package com.karsatech.steamapps.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karsatech.steamapps.core.domain.model.Steam
import com.karsatech.steamapps.core.domain.usecase.SteamUseCase

class DetailAppsViewModel(private val steamUseCase: SteamUseCase) : ViewModel() {

    fun setFavoriteApps(steam: Steam, newStatus: Boolean) =
        steamUseCase.setFavoriteSteam(steam, newStatus)

    fun getDetailApps(id: String) = steamUseCase.getDetailSteam(id).asLiveData()

}