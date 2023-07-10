package com.karsatech.steamapps.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karsatech.steamapps.core.di.Injection
import com.karsatech.steamapps.core.domain.usecase.SteamUseCase
import com.karsatech.steamapps.detail.DetailAppsViewModel
import com.karsatech.steamapps.favorite.FavoriteViewModel
import com.karsatech.steamapps.home.HomeViewModel

class ViewModelFactory private constructor(private val steamUseCase: SteamUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideTourismUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(steamUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(steamUseCase) as T
            }
            modelClass.isAssignableFrom(DetailAppsViewModel::class.java) -> {
                DetailAppsViewModel(steamUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}