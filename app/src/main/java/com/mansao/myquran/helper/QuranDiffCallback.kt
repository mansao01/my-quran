package com.mansao.myquran.helper

import androidx.recyclerview.widget.DiffUtil
import com.mansao.myquran.data.network.response.ResponseItem

class QuranDiffCallback(
    private val mOldQuranList: List<ResponseItem>,
    private val mNewQuranList: List<ResponseItem>
) : DiffUtil.Callback() {
    override fun getOldListSize() = mOldQuranList.size

    override fun getNewListSize() = mNewQuranList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldQuranList[oldItemPosition].nomor ==  mNewQuranList[newItemPosition].nomor
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldQuranList[oldItemPosition]
        val newEmployee = mNewQuranList[newItemPosition]
        return oldEmployee.nama == newEmployee.nama
    }
}