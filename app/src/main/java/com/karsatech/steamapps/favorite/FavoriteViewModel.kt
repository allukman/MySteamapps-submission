package com.karsatech.steamapps.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karsatech.steamapps.core.domain.usecase.SteamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(steamUseCase: SteamUseCase) : ViewModel() {
    val favoriteTourism = steamUseCase.getFavoriteSteam().asLiveData()
}