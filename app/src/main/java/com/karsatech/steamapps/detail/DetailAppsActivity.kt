package com.karsatech.steamapps.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.karsatech.steamapps.R
import com.karsatech.steamapps.core.data.Resource
import com.karsatech.steamapps.core.data.source.remote.response.DetailSteamResponse
import com.karsatech.steamapps.core.domain.model.Steam
import com.karsatech.steamapps.core.ui.ViewModelFactory
import com.karsatech.steamapps.core.utils.applyStrikeThrough
import com.karsatech.steamapps.core.utils.withCurrencyFormat
import com.karsatech.steamapps.databinding.ActivityDetailAppsBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailAppsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAppsBinding
    private lateinit var detailAppsViewModel: DetailAppsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAppsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val factory = ViewModelFactory.getInstance(this)
        detailAppsViewModel = ViewModelProvider(this, factory)[DetailAppsViewModel::class.java]

        val detailSteam = intent.getParcelableExtra<Steam>(EXTRA_DATA)
        getDetailApp(detailSteam!!)
        supportActionBar?.title = detailSteam.name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun getDetailApp(detail: Steam) {
        detailAppsViewModel.getDetailApps(detail.steamId.toString()).observe(this) { data ->
            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        showDetailSteamApp(data.data!!)
                        showSteamApp(detail)
                    }

                    is Resource.Error -> {
                        Log.d("DetailActivity", "Error")
                    }
                }
            }
        }
    }

    private fun showSteamApp(data: Steam?) {
        data?.let {

            binding.content.tvDiscount.text = "-${it.discountPercent}%"
            binding.content.tvOriginalPrice.text = data.originalPrice.withCurrencyFormat()
            binding.content.tvFinalPrice.text = data.finalPrice.withCurrencyFormat()

            binding.content.tvOriginalPrice.applyStrikeThrough()

            Glide.with(this)
                .load(it.headerImage)
                .into(binding.ivDetailImage)

            Glide.with(this)
                .load(it.largeImage)
                .into(binding.content.ivBanner)



            var statusFavorite = it.isFavorite
            setStatusFavorite(statusFavorite)

            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailAppsViewModel.setFavoriteApps(data, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }

        binding.content.ivBanner.setOnClickListener {
            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra(ImageActivity.TAG, data!!.largeImage)
            startActivity(intent)
        }

    }

    private fun showDetailSteamApp(data: DetailSteamResponse) {
        binding.content.snippet.text = data.snippet
        binding.content.description.text = data.description
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_black))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}