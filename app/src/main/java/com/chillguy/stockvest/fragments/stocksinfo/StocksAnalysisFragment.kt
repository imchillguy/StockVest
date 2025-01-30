package com.chillguy.stockvest.fragments.stocksinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chillguy.stockvest.R
import com.chillguy.stockvest.adapter.HorizontalBarAdapter
import com.chillguy.stockvest.constants.StocksConstants
import com.chillguy.stockvest.databinding.FragmentStocksAnalysisBinding
import com.chillguy.stockvest.repository.StockAnalysisRepository
import com.chillguy.stockvest.viewmodel.factory.VIewModelFactory.createFactory
import com.chillguy.stockvest.viewmodel.stocksinfo.StocksAnalysisViewModel

class StocksAnalysisFragment : Fragment() {

    private lateinit var binding: FragmentStocksAnalysisBinding
    private lateinit var viewModel: StocksAnalysisViewModel
    private lateinit var stocksSymbol: String
    private lateinit var stocksAnalysisBarAdapter: HorizontalBarAdapter

    companion object {
        fun newInstance(bundle: Bundle): StocksAnalysisFragment {
            return StocksAnalysisFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stocksSymbol = arguments?.getString(StocksConstants.STOCKS_SYMBOL) ?: ""
        val factory = StocksAnalysisViewModel(
            stocksAnalysisRepository = StockAnalysisRepository(stocksSymbol)
        ).createFactory()
        viewModel = ViewModelProvider(this, factory)[StocksAnalysisViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stocks_analysis, container, false)
        binding.stocksAnalysisViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            stocksAnalysisBarRv.apply {
                stocksAnalysisBarAdapter = HorizontalBarAdapter()
                adapter = stocksAnalysisBarAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.stocksAnalysisData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                binding.stocksRatingAnalysisTv.text = data.stocksAnalysisText
                binding.stocksPriceAnalysisTv.text = data.stocksPriceAnalysisText
                stocksAnalysisBarAdapter.horizontalBarList = data.stocksAnalysisDetailList
                binding.stocksPriceTargetView.bindData(data.stocksPriceTargetData)
            }
        })
    }
}