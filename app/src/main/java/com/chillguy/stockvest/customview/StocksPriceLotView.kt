package com.chillguy.stockvest.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.adapter.StocksPriceLotAdapter
import com.chillguy.stockvest.customview.type.StocksPriceLotViewType
import com.chillguy.stockvest.itemdecoration.GridSpaceBetweenItemDecoration
import com.chillguy.stockvest.model.StocksPriceLotData
import com.chillguy.stockvest.utils.Utils.toPx

class StocksPriceLotView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): LinearLayout(context, attrs) {

    private lateinit var stockPriceLotRecyclerView: RecyclerView
    private lateinit var stocksPriceLotAdapter: StocksPriceLotAdapter
    private lateinit var type: String

    init {
        LayoutInflater.from(context).inflate(R.layout.stocks_price_lot_view, this, true)
        context.theme.obtainStyledAttributes(attrs, R.styleable.StocksPriceLotView, 0 ,0).apply {
            type = getString(R.styleable.StocksPriceLotView_viewType) ?: StocksPriceLotViewType.NAME_BOLD.type
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        stockPriceLotRecyclerView = findViewById(R.id.stocks_price_lot_rv)
        stocksPriceLotAdapter = StocksPriceLotAdapter(type)
    }

    fun bindData(stocksPriceLotData: List<StocksPriceLotData>) {
        with(stockPriceLotRecyclerView) {
            stocksPriceLotAdapter.stocksPriceLotDataList = stocksPriceLotData
            adapter = stocksPriceLotAdapter
            layoutManager = GridLayoutManager(context, 2)
            val space = context.toPx(36).toInt()
            addItemDecoration(GridSpaceBetweenItemDecoration(space))
        }
    }

}