package com.chillguy.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.diffutil.HorizontalBarDiffUtilCallback
import com.chillguy.stockvest.model.HorizontalBarData
import com.chillguy.stockvest.viewholder.HorizontalBarViewHolder

class HorizontalBarAdapter: RecyclerView.Adapter<HorizontalBarViewHolder>() {

    var horizontalBarList: List<HorizontalBarData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ: AsyncListDiffer<HorizontalBarData> = AsyncListDiffer(this, HorizontalBarDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalBarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_bar_with_text, parent, false)
        return HorizontalBarViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return horizontalBarList.size
    }

    override fun onBindViewHolder(holder: HorizontalBarViewHolder, position: Int) {
        holder.bindData(horizontalBarList[position])
    }
}