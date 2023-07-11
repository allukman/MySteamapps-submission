package com.karsatech.steamapps.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karsatech.steamapps.core.R
import com.karsatech.steamapps.core.domain.model.Steam
import com.karsatech.steamapps.core.utils.applyStrikeThrough
import com.karsatech.steamapps.core.utils.withCurrencyFormat
import com.karsatech.steamapps.core.databinding.ItemListTourismBinding

class SteamAdapter : RecyclerView.Adapter<SteamAdapter.ListViewHolder>() {

    private var listData = ArrayList<Steam>()
    var onItemClick: ((Steam) -> Unit)? = null

    fun setData(newListData: List<Steam>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_tourism, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListTourismBinding.bind(itemView)
        fun bind(data: Steam) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.headerImage)
                    .into(ivItemImage)
                tvItemTitle.text = data.name
                tvDiscount.text = "-${data.discountPercent}%"
                tvOriginalPrice.text = data.originalPrice.withCurrencyFormat()
                tvFinalPrice.text = data.finalPrice.withCurrencyFormat()

                tvOriginalPrice.applyStrikeThrough()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
