package com.chillguy.stockvest.customview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.bumptech.glide.Glide
import com.chillguy.stockvest.R
import com.chillguy.stockvest.adapter.StocksCategoryAdapter
import com.chillguy.stockvest.adapter.StocksPriceLotAdapter
import com.chillguy.stockvest.customview.behaviour.StockChartDetailViewBehaviour
import com.chillguy.stockvest.customview.type.StockChartDetailViewType
import com.chillguy.stockvest.databinding.StocksChartDetailViewBinding
import com.chillguy.stockvest.enums.StocksMomentum
import com.chillguy.stockvest.extension.SpannableExtension.getStocksPriceColoredSpan
import com.chillguy.stockvest.listener.IFilterOptionsClickListener
import com.chillguy.stockvest.model.FilterOptionsData
import com.chillguy.stockvest.model.StocksChartDetailData
import com.chillguy.stockvest.model.StocksInfoData
import com.chillguy.stockvest.model.StocksPriceLotData
import com.chillguy.stockvest.viewmodel.StocksChartDetailViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxItemDecoration
import com.google.android.flexbox.FlexboxLayoutManager

class StocksChartDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet ?= null
) : ConstraintLayout(context, attrs){

    private lateinit var binding: StocksChartDetailViewBinding
    private lateinit var stocksPriceLotAdapter: StocksPriceLotAdapter
    private lateinit var type: String

    private val viewModel by lazy {
        ViewModelProvider(findViewTreeViewModelStoreOwner()!!)[StocksChartDetailViewModel::class.java]
    }

    init {
        binding = StocksChartDetailViewBinding.inflate(LayoutInflater.from(context),this, true)
        context.theme.obtainStyledAttributes(attrs, R.styleable.StocksChartDetailView, 0, 0).apply {
            type = getString(R.styleable.StocksChartDetailView_type) ?: StockChartDetailViewType.STOCK_PRICE_CHART.type
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        with(getBehaviour()) {
            binding.stocksInfoRl.visibility = stocksInfoVisibility
            binding.stocksSymbolTv.visibility = stocksSymbolVisibility
            binding.stocksCategoryRv.visibility = stocksCategoryVisibility
            binding.stocksChartTimeframeFilter.visibility = stocksChartTimeFrameVisibility
            binding.stocksPriceLotView.visibility = stocksPriceLotVisibility
        }
        viewModel.stocksChartTimeFrameFilterList.observe(findViewTreeLifecycleOwner()!!, Observer { list ->
            binding.stocksChartTimeframeFilter.updateFilteredOptionsList(list)
        })
        viewModel.stocksChartData.observe(findViewTreeLifecycleOwner()!!, Observer { list ->
            binding.stocksChartView.animate(list)
        })
        viewModel.stocksPrice.observe(findViewTreeLifecycleOwner()!!, Observer { price ->
            binding.stocksPriceTv.text = price
        })
    }

    private fun getBehaviour(): StockChartDetailViewBehaviour {
        return when(type) {
            StockChartDetailViewType.STOCK_PRICE_CHART.type -> {
                StockChartDetailViewBehaviour.StockPriceChartView
            }
            StockChartDetailViewType.STOCK_DETAIL_PRICE_CHART.type -> {
                StockChartDetailViewBehaviour.StockDetailPriceChartView
            }
            else -> {
                StockChartDetailViewBehaviour.StockPriceChartView
            }
        }
    }

    fun setViewModel(){
        binding.stockDetailsViewModel = viewModel
    }

    fun bindData(stocksChartDetailData: StocksChartDetailData) {
        with(stocksChartDetailData) {
            viewModel.stocksSymbol.value = stocksInfoData.stocksSymbol
            viewModel.stocksPrice.value = stocksInfoData.stocksPrice
            renderStocksInfo(stocksChartDetailData.stocksInfoData)
            renderStocksChart(stocksChartData, stocksInfoData.stocksMomentum)
            renderStocksCategory(stocksChartDetailData.stocksCategory)
            renderStocksChartTimeFrame(stocksTimeFrameData)
            renderStockCurrentPriceChange(stocksInfoData.stocksPriceChange, stocksInfoData.stocksMomentum)
            renderStockPriceAndLotDetails(stocksPriceLotData)
        }
    }

    private fun renderStocksCategory(stocksCategory: List<String>) {
        val itemDecoration = FlexboxItemDecoration(context)
        itemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.space_item_decorator))
        binding.stocksCategoryRv.apply {
            adapter = StocksCategoryAdapter(stocksCategory)
            layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW_REVERSE)
            addItemDecoration(itemDecoration)
        }
    }

    private fun renderStocksInfo(stocksInfoData: StocksInfoData) {
        with(binding) {
            Glide.with(context)
                .load(stocksInfoData.stocksIconUrl)
                .centerCrop()
                .into(stocksIconIv)
            stocksInfoSymbolTv.text = stocksInfoData.stocksSymbol
            stocksNameTv.text = stocksInfoData.stocksName
        }
    }

    private fun renderStocksChartTimeFrame(stocksTimeFrameData: List<FilterOptionsData>) {
        with(binding) {
            stocksChartTimeframeFilter.bindData(object : IFilterOptionsClickListener{
                override fun onFilterOptionClick(position: Int, text: String) {
                    viewModel.updateList(position)
                    viewModel.updateChartWithTimeFrame()
                }
            })
            viewModel.setFilterOptionsDataList(stocksTimeFrameData)
        }
    }

    private fun renderStocksChart(
        stockChartData: List<Pair<String, Float>>,
        stocksMomentum: StocksMomentum
    ) {
        with(binding.stocksChartView) {
            animate(stockChartData)
            when (stocksMomentum) {
                StocksMomentum.BULLISH -> {
                    gradientFillColors = intArrayOf(
                        context.getColor(R.color.green_40),
                        Color.TRANSPARENT
                    )
                    lineColor = context.getColor(R.color.green_60)
                }
                StocksMomentum.BEARISH -> {
                    gradientFillColors = intArrayOf(
                        context.getColor(R.color.claret_20),
                        Color.TRANSPARENT
                    )
                    lineColor = context.getColor(R.color.claret_50)
                }
            }
        }
    }

    private fun renderStockCurrentPriceChange(stocksPriceChange: String, stocksMomentum: StocksMomentum) {
        viewModel.stocksPriceChange.value = context.getStocksPriceColoredSpan(stocksPriceChange, stocksMomentum)
    }

    private fun renderStockPriceAndLotDetails(stocksPriceLotData: List<StocksPriceLotData>) {
        with(binding.stocksPriceLotView) {
            post {
                bindData (stocksPriceLotData)
            }
        }
    }


}