package com.karsatech.steamapps.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.karsatech.steamapps.core.data.Resource
import com.karsatech.steamapps.core.ui.SteamAdapter
import com.karsatech.steamapps.detail.DetailAppsActivity
import com.karsatech.steamapps.di.FavoriteModuleDependencies
import com.karsatech.steamapps.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val steamAdapter = SteamAdapter()
        steamAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailAppsActivity::class.java)
            intent.putExtra(DetailAppsActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteTourism.observe(this) { dataTourism ->
            steamAdapter.setData(dataTourism)

            binding.viewEmptyFavorite.root.visibility =
                if (dataTourism.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvTourism) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = steamAdapter
        }

    }
}