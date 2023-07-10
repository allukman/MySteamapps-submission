package com.karsatech.steamapps.core.domain.usecase

import com.karsatech.steamapps.core.domain.model.Steam
import com.karsatech.steamapps.core.domain.repository.ISteamRepository
import javax.inject.Inject

class SteamInteractor @Inject constructor(private val tourismRepository: ISteamRepository): SteamUseCase {

    override fun getAllSteam() = tourismRepository.getAllSteam()

    override fun getFavoriteSteam() = tourismRepository.getFavoriteSteam()

    override fun setFavoriteSteam(steam: Steam, state: Boolean) = tourismRepository.setFavoriteSteam(steam, state)

    override fun getDetailSteam(id: String) = tourismRepository.getDetailSteam(id)

}