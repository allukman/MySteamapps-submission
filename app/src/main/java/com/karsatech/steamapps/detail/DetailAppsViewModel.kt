package com.karsatech.steamapps.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karsatech.steamapps.core.domain.model.Steam
import com.karsatech.steamapps.core.domain.usecase.SteamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailAppsViewModel @Inject constructor(private val steamUseCase: SteamUseCase) : ViewModel() {

    fun setFavoriteApps(steam: Steam, newStatus: Boolean) =
        steamUseCase.setFavoriteSteam(steam, newStatus)

    fun getDetailApps(id: String) = steamUseCase.getDetailSteam(id).asLiveData()

}