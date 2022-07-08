package com.lauravelasquezcano.melichallenge.app.ui.main.results

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lauravelasquezcano.melichallenge.R
import com.lauravelasquezcano.melichallenge.databinding.ResultItemBinding
import com.lauravelasquezcano.melichallenge.domain.Item
import javax.inject.Inject

class ResultsAdapter @Inject constructor() :
    RecyclerView.Adapter<ResultsAdapter.ResultViewHolder>() {

    private lateinit var data: List<Item>

    var saveItem: ((Item) -> Unit)? = null

    fun setData(itemList: List<Item>) {
        data = itemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder =
        ResultViewHolder(
            ResultItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.initListener(item)
    }

    override fun getItemCount(): Int = data.size

    inner class ResultViewHolder(private val binding: ResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            with(binding) {
                if (item.title.isNullOrEmpty()) tvItemTitle.visibility =
                    View.GONE else tvItemTitle.text = item.title
                if (item.price.toString().isNullOrEmpty()) tvItemPrice.visibility =
                    View.GONE else tvItemPrice.text = item.price.toString()
                if (item.currency_id.isNullOrEmpty()) tvCurrency.visibility =
                    View.GONE else tvCurrency.text = item.currency_id
            }
            Glide.with(binding.root.context)
                .load(item.thumbnail)
                .override(IMAGE_SIZE, IMAGE_SIZE)
                .placeholder(R.drawable.ic_image_holder)
                .error(R.drawable.ic_broken_image)
                .into(binding.ivItemImage)
        }

        fun initListener(item: Item) {
            itemView.setOnClickListener {
                saveItem?.invoke(item)
            }
        }
    }

    companion object {
        const val IMAGE_SIZE = 100
    }
}