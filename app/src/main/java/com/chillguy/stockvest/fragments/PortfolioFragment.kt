package com.chillguy.stockvest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chillguy.stockvest.R
import com.chillguy.stockvest.adapter.StocksInvestmentAdapter
import com.chillguy.stockvest.databinding.FragmentPortfolioBinding
import com.chillguy.stockvest.itemdecoration.HorizontalSpaceAndSeparatorItemDecoration
import com.chillguy.stockvest.utils.Utils.toPx
import com.chillguy.stockvest.viewmodel.PortfolioViewModel

class PortfolioFragment : Fragment() {

    private lateinit var binding: FragmentPortfolioBinding
    private lateinit var viewModel: PortfolioViewModel
    private lateinit var stocksInvestmentAdapter: StocksInvestmentAdapter

    companion object {
        const val TAG = "PortfolioFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PortfolioViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_portfolio, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            stocksInvestmentAdapter = StocksInvestmentAdapter()
            stocksPortfolioRv.apply {
                adapter = stocksInvestmentAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(HorizontalSpaceAndSeparatorItemDecoration(requireContext(), 0, ContextCompat.getDrawable(requireContext(), R.drawable.horizontal_divider_line)))
            }
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.stocksInvestmentData.observe(viewLifecycleOwner, Observer { list ->
            stocksInvestmentAdapter.stocksInvestmentDataList = list
        })
    }
}