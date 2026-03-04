package com.karsatech.steamapps.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.karsatech.steamapps.R
import com.karsatech.steamapps.core.data.Resource.Error
import com.karsatech.steamapps.core.data.Resource.Loading
import com.karsatech.steamapps.core.data.Resource.Success
import com.karsatech.steamapps.core.ui.SteamAdapter
import com.karsatech.steamapps.databinding.FragmentHomeBinding
import com.karsatech.steamapps.detail.DetailAppsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val steamAdapter = SteamAdapter()
            steamAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailAppsActivity::class.java)
                intent.putExtra(DetailAppsActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.steamData.observe(viewLifecycleOwner) { steam ->
                if (steam != null) {
                    when (steam) {
                        is Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Success -> {
                            binding.progressBar.visibility = View.GONE
                            Log.d("STEAM_ID", steam.data?.map { it.steamId }.toString())
                            steamAdapter.setData(steam.data.orEmpty())
                        }

                        is Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                steam.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvSteam) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = steamAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}