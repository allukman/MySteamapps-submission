package com.karsatech.steamapps.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karsatech.steamapps.core.domain.usecase.SteamUseCase

class FavoriteViewModel(steamUseCase: SteamUseCase) : ViewModel() {
    val favoriteTourism = steamUseCase.getFavoriteSteam().asLiveData()
}