package com.chillguy.stockvest.fragments

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
import com.chillguy.stockvest.adapter.TransactionDayAdapter
import com.chillguy.stockvest.databinding.FragmentTransactionsBinding
import com.chillguy.stockvest.listener.IFilterOptionsClickListener
import com.chillguy.stockvest.viewmodel.TransactionsViewModel

class TransactionsFragment : Fragment(), IFilterOptionsClickListener {

    private lateinit var binding: FragmentTransactionsBinding
    private lateinit var viewModel: TransactionsViewModel
    private lateinit var transactionDayAdapter: TransactionDayAdapter

    companion object {
        const val TAG = "TransactionsFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TransactionsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_transactions, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            transactionDayAdapter = TransactionDayAdapter()
            transactionsRv.apply {
                adapter = transactionDayAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
            transactionsFilter.bindData(this@TransactionsFragment)
            viewModel.setFilterOptionsDataList(viewModel.getTransactionsFilteredOptionsList())
        }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.transactionsFilterList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                binding.transactionsFilter.updateFilteredOptionsList(list)
            }
        })
        viewModel.transactionsDayData.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                transactionDayAdapter.transactionDayDataList = list
            }
        })
    }

    override fun onFilterOptionClick(position: Int, text: String) {
        viewModel.updateList(position)
    }
}