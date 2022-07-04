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

class ResultsAdapter : RecyclerView.Adapter<ResultsAdapter.ResultViewHolder>() {

    private lateinit var context: Context
    private lateinit var data: List<Item>

    fun setData(itemList: List<Item>) {
        data = itemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding = ResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
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
            Glide.with(context)
                .load(item.thumbnail)
                .override(100, 100)
                .placeholder(R.drawable.ic_image_holder)
                .error(R.drawable.ic_broken_image)
                .into(binding.ivItemImage)
        }
    }
}