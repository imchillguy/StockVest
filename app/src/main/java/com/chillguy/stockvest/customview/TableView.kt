package com.chillguy.stockvest.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.adapter.TableAdapter
import com.chillguy.stockvest.model.TableData

class TableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): LinearLayout(context, attrs) {

    private lateinit var tableRecyclerView: RecyclerView
    private val tableAdapter: TableAdapter by lazy { TableAdapter() }

    init {
        LayoutInflater.from(context).inflate(R.layout.table_view, this, true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        tableRecyclerView = findViewById(R.id.table_rv)
        with(tableRecyclerView) {
            adapter = tableAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    fun bindData(list: List<TableData>) {
        tableAdapter.tableList = list
    }

}