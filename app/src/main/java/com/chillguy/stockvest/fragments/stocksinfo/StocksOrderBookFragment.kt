package com.chillguy.stockvest.fragments.stocksinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chillguy.stockvest.R
import com.chillguy.stockvest.constants.StocksConstants
import com.chillguy.stockvest.databinding.FragmentStocksOrderBookBinding
import com.chillguy.stockvest.repository.StocksOrderBookRepository
import com.chillguy.stockvest.viewmodel.stocksinfo.StocksOrderBookViewModel
import com.chillguy.stockvest.viewmodel.factory.VIewModelFactory.createFactory

class StocksOrderBookFragment : Fragment() {

    private lateinit var binding: FragmentStocksOrderBookBinding
    private lateinit var viewModel: StocksOrderBookViewModel
    private lateinit var stocksSymbol: String
    private val stocksOrderBookTitle: String = "OrderBook"

    companion object {
        fun newInstance(bundle: Bundle): StocksOrderBookFragment {
            return StocksOrderBookFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stocksSymbol = arguments?.getString(StocksConstants.STOCKS_SYMBOL) ?: ""
        val factory = StocksOrderBookViewModel(
            stocksOrderBookRepository = StocksOrderBookRepository(stocksSymbol)
        ).createFactory()
        viewModel = ViewModelProvider(this, factory)[StocksOrderBookViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stocks_order_book, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            stocksOrderbookTv.text = stocksOrderBookTitle
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.stocksOrderBookPriceLot.observe(viewLifecycleOwner, Observer { list ->
            binding.stocksOrderbookPriceLot.bindData(list)
        })
        viewModel.stocksOrderBookBidAskLot.observe(viewLifecycleOwner, Observer { list ->
            binding.stocksOrderbookTableView.bindData(list)
        })
    }
}