package com.chillguy.stockvest.fragments.stocksinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.adapter.NewsAdapter
import com.chillguy.stockvest.constants.StocksConstants
import com.chillguy.stockvest.repository.StocksNewsRepository
import com.chillguy.stockvest.viewmodel.factory.VIewModelFactory.createFactory
import com.chillguy.stockvest.viewmodel.stocksinfo.StocksNewsViewModel

class StocksNewsFragment : Fragment() {

    private lateinit var stocksSymbol: String
    private lateinit var viewModel: StocksNewsViewModel
    private lateinit var stocksNewsAdapter: NewsAdapter

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) =
            StocksNewsFragment().apply {
                arguments = bundle
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stocksSymbol = arguments?.getString(StocksConstants.STOCKS_SYMBOL) ?: ""
        val factory = StocksNewsViewModel(
            stocksNewsRepository = StocksNewsRepository(stocksSymbol)
        ).createFactory()
        viewModel = ViewModelProvider(this, factory)[StocksNewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stocks_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view) {
            val stocksNewsTextView = findViewById<TextView>(R.id.stocks_news_tv)
            stocksNewsTextView.text = "News"
            val stocksNewsRecyclerView = findViewById<RecyclerView>(R.id.stocks_news_rv)
            stocksNewsAdapter = NewsAdapter()
            stocksNewsRecyclerView.apply {
                adapter = stocksNewsAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.stocksNewsDataList.observe(viewLifecycleOwner, Observer { list ->
            stocksNewsAdapter.newsDataList = list
        })
    }
}