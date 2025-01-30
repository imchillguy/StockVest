package com.chillguy.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.diffutil.TableDiffUtilCallback
import com.chillguy.stockvest.model.TableData
import com.chillguy.stockvest.viewholder.TableViewHolder

class TableAdapter: RecyclerView.Adapter<TableViewHolder>() {

    var tableList: List<TableData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ: AsyncListDiffer<TableData> = AsyncListDiffer(this, TableDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.table_item_view, parent, false)
        return TableViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tableList.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bindData(tableList[position], position)
    }
}