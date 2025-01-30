package com.chillguy.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.diffutil.FilterOptionsDiffUtilCallback
import com.chillguy.stockvest.listener.IFilterOptionsClickListener
import com.chillguy.stockvest.model.FilterOptionsData
import com.chillguy.stockvest.viewholder.FilterOptionsViewHolder

class FilterOptionsAdapter(private val listener: IFilterOptionsClickListener, private val type: String): RecyclerView.Adapter<FilterOptionsViewHolder>() {

    var filterOptionsDataList: List<FilterOptionsData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ: AsyncListDiffer<FilterOptionsData> = AsyncListDiffer(this, FilterOptionsDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterOptionsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.filter_options_item, parent, false)
        return FilterOptionsViewHolder(itemView, type)
    }

    override fun getItemCount(): Int {
        return filterOptionsDataList.size
    }

    override fun onBindViewHolder(holder: FilterOptionsViewHolder, position: Int) {
        holder.bindData(filterOptionsDataList[position])
        holder.itemView.setOnClickListener {
            listener.onFilterOptionClick(filterOptionsDataList[position].id, filterOptionsDataList[position].text)
        }
    }
}