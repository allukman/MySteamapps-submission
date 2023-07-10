package com.karsatech.steamapps.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karsatech.steamapps.core.domain.usecase.SteamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(steamUseCase: SteamUseCase) : ViewModel() {
    val tourism = steamUseCase.getAllSteam().asLiveData()
}