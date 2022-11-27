package com.mansao.myquran.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mansao.myquran.data.network.response.ResponseItem
import com.mansao.myquran.databinding.ListItemBinding
import com.mansao.myquran.helper.QuranDiffCallback
import com.mansao.myquran.ui.DetailActivity

class ListQuranAdapter : RecyclerView.Adapter<ListQuranAdapter.ListQuranViewHolder>() {
    private val listQuran = ArrayList<ResponseItem>()

    fun setListQuran(listQuran: List<ResponseItem>) {
        val diffCallback = QuranDiffCallback(this.listQuran, listQuran)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listQuran.clear()
        this.listQuran.addAll(listQuran)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListQuranViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListQuranViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListQuranViewHolder, position: Int) {
        holder.bind(listQuran[position])
    }

    override fun getItemCount() = listQuran.size

    inner class ListQuranViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listQuran: ResponseItem) {
            binding.apply {
                tvTitle.text = StringBuilder(listQuran.nama).append(" (${listQuran.asma})")
                tvMeaning.text = StringBuilder(listQuran.arti).append(" · ${listQuran.ayat} ayat")
                cardView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, listQuran)
                    itemView.context.startActivity(intent)
                }
            }
        }


    }

}