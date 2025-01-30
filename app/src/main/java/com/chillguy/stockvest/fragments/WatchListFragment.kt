package com.chillguy.stockvest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.adapter.StocksInfoAdapter
import com.chillguy.stockvest.callbacks.SwipeToDeleteCallback
import com.chillguy.stockvest.itemdecoration.HorizontalSpaceAndSeparatorItemDecoration
import com.chillguy.stockvest.utils.Utils.toPx
import com.chillguy.stockvest.viewmodel.WatchListViewModel

class WatchListFragment : Fragment() {

    private lateinit var stocksInfoAdapter: StocksInfoAdapter
    private lateinit var viewModel: WatchListViewModel

    companion object {
        const val TAG = "WatchListFragment"
    }

    private val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            val position = viewHolder.adapterPosition
            var stocksInfoDataList = stocksInfoAdapter.stocksInfoDataList
            stocksInfoDataList -= stocksInfoDataList[position]
            stocksInfoAdapter.stocksInfoDataList = stocksInfoDataList
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[WatchListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watch_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view) {
            stocksInfoAdapter = StocksInfoAdapter(null)
            val watchListRecyclerView = findViewById<RecyclerView>(R.id.watchlist_rv)
            watchListRecyclerView.apply {
                adapter = stocksInfoAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                val itemDecoration = HorizontalSpaceAndSeparatorItemDecoration(requireContext(), context.toPx(10).toInt(), ContextCompat.getDrawable(requireContext(), R.drawable.horizontal_divider_line))
                addItemDecoration(itemDecoration)
            }
            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
            itemTouchHelper.attachToRecyclerView(watchListRecyclerView)
        }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.stocksWatchList.observe(viewLifecycleOwner, Observer { list ->
            stocksInfoAdapter.stocksInfoDataList = list
        })
    }
}