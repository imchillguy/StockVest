package com.chillguy.stockvest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chillguy.stockvest.R
import com.chillguy.stockvest.bottomsheet.StocksBuyBottomSheet
import com.chillguy.stockvest.constants.StocksConstants
import com.chillguy.stockvest.databinding.FragmentStocksDetailInfoBinding
import com.chillguy.stockvest.enums.StocksDetailInfoActionType
import com.chillguy.stockvest.fragments.stocksinfo.StocksAnalysisFragment
import com.chillguy.stockvest.fragments.stocksinfo.StocksNewsFragment
import com.chillguy.stockvest.fragments.stocksinfo.StocksOrderBookFragment
import com.chillguy.stockvest.listener.IFilterOptionsClickListener
import com.chillguy.stockvest.listener.IShowBottomSheet
import com.chillguy.stockvest.viewmodel.StocksChartDetailViewModel
import com.chillguy.stockvest.viewmodel.StocksDetailInfoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StocksDetailInfoFragment : Fragment(), IFilterOptionsClickListener, IShowBottomSheet {

    private lateinit var binding: FragmentStocksDetailInfoBinding
    private lateinit var viewModel: StocksDetailInfoViewModel
    private lateinit var stocksChartDetailViewModel: StocksChartDetailViewModel

    companion object {
        const val TAG = "StocksDetailInfoFragment"

        fun newInstance(bundle: Bundle?): StocksDetailInfoFragment {
            return StocksDetailInfoFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[StocksDetailInfoViewModel::class.java]
        stocksChartDetailViewModel = ViewModelProvider(this)[StocksChartDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stocks_detail_info, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            backIv.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
            }
            stocksChartDetailView.setViewModel()
            stocksChartDetailView.bindData(stocksChartDetailViewModel.getStocksChartDetailsData())
            stocksOptionsFilter.bindData(this@StocksDetailInfoFragment)
            viewModel.setFilterOptionsDataList(viewModel.getStocksFilteredOptionsList())
            stocksBuyBtn.setOnClickListener {
                val stocksBuyBottomSheet = StocksBuyBottomSheet()
                stocksBuyBottomSheet.initListener(this@StocksDetailInfoFragment)
                stocksBuyBottomSheet.show(childFragmentManager, StocksBuyBottomSheet.TAG)
            }
        }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.stocksDetailInfoFilterList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                binding.stocksOptionsFilter.updateFilteredOptionsList(list)
            }
        })
    }

    override fun onFilterOptionClick(position: Int, text: String) {
        viewModel.updateList(position)
        stocksInfoActionHandling(text)
    }

    private fun stocksInfoActionHandling(text: String) {
        val bundle = bundleOf(StocksConstants.STOCKS_SYMBOL to text)
        val fragment = when (text) {
            StocksDetailInfoActionType.ANALYSIS.type -> StocksAnalysisFragment.newInstance(bundle)
            StocksDetailInfoActionType.NEWS.type -> StocksNewsFragment.newInstance(bundle)
            StocksDetailInfoActionType.ORDERBOOK.type -> StocksOrderBookFragment.newInstance(bundle)
            else -> StocksAnalysisFragment.newInstance(bundle)
        }
        childFragmentManager.beginTransaction()
            .replace(R.id.stocks_info_container, fragment)
            .commit()
    }

    override fun showBottomSheet(bottomSheetInstance: BottomSheetDialogFragment, tag: String) {
        bottomSheetInstance.show(childFragmentManager, tag)
    }

}