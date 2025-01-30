package com.chillguy.stockvest.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chillguy.stockvest.R
import com.chillguy.stockvest.adapter.StocksInfoAdapter
import com.chillguy.stockvest.constants.ApiConstants
import com.chillguy.stockvest.constants.StocksConstants
import com.chillguy.stockvest.databinding.FragmentHomeBinding
import com.chillguy.stockvest.listener.IFilterOptionsClickListener
import com.chillguy.stockvest.listener.INavigationListener
import com.chillguy.stockvest.listener.IOnStocksClickListener
import com.chillguy.stockvest.listener.IStocksCurrentPrice
import com.chillguy.stockvest.model.FragmentTransactionModel
import com.chillguy.stockvest.viewmodel.HomeViewModel
import com.chillguy.stockvest.viewmodel.StocksChartDetailViewModel
import com.chillguy.stockvest.websockets.StocksPriceWebSocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class HomeFragment : Fragment(), IFilterOptionsClickListener, IOnStocksClickListener, IStocksCurrentPrice {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var stocksChartDetailViewModel: StocksChartDetailViewModel
    private lateinit var stocksInfoAdapter: StocksInfoAdapter
    private lateinit var navigationListener: INavigationListener
    private lateinit var stocksPriceWebSocket: StocksPriceWebSocket
    private val okHttpClient = OkHttpClient()
    private var webSocket: WebSocket? = null

    companion object {
        const val TAG = "HomeFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationListener = context as INavigationListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        stocksChartDetailViewModel = ViewModelProvider(this)[StocksChartDetailViewModel::class.java]
        stocksPriceWebSocket = StocksPriceWebSocket(this@HomeFragment)
        webSocket = okHttpClient.newWebSocket(createWebSocketRequest(), stocksPriceWebSocket)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            stockChartDetailView.setViewModel()
            stockChartDetailView.bindData(stocksChartDetailViewModel.getStocksDetailsData())
            stocksOptionsFilter.bindData(this@HomeFragment)
            viewModel.setFilterOptionsDataList(viewModel.getStocksFilteredOptionsList())
            stocksInfoAdapter = StocksInfoAdapter(this@HomeFragment)
            stocksInfoRv.apply {
                adapter = stocksInfoAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.stocksInfoDataList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                stocksInfoAdapter.stocksInfoDataList = list
            }
        })
        viewModel.stocksOptionFilterList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                binding.stocksOptionsFilter.updateFilteredOptionsList(list)
            }
        })
        viewModel.stocksSymbolsList.observe(viewLifecycleOwner, Observer { list ->
            //viewModel.fetchStocksData(list)
            viewModel.setStocksInfoData()
        })

    }

    private fun createWebSocketRequest(): Request {
        return Request.Builder()
            .url("wss://ws.twelvedata.com/v1/quotes/price?apikey=${ApiConstants.TWELVE_API_KEY}")
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocket?.close(1000, "Cancelled")
    }

    override fun onFilterOptionClick(position: Int, text: String) {
        viewModel.updateList(position)
    }

    override fun onStocksClicked(stocksSymbol: String) {
        val stocksDetailsInfoFragmentModel = FragmentTransactionModel(
            fragment = StocksDetailInfoFragment::class.java,
            bundle = bundleOf(StocksConstants.STOCKS_SYMBOL to stocksSymbol),
            addToBackStack = true,
            tag = StocksDetailInfoFragment.TAG
        )
        navigationListener.navigateToFragment(stocksDetailsInfoFragmentModel)
    }

    override fun getStocksCurrentPrice(stocksPriceData: String) {
        stocksChartDetailViewModel.updateStocksPriceChart(stocksPriceData)
    }

}