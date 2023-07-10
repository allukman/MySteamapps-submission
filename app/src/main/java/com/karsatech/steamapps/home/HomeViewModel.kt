package com.karsatech.steamapps.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karsatech.steamapps.core.domain.usecase.SteamUseCase

class HomeViewModel(steamUseCase: SteamUseCase) : ViewModel() {
    val tourism = steamUseCase.getAllSteam().asLiveData()
}