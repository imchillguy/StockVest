package com.chillguy.stockvest.fragments.stocksinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chillguy.stockvest.R

class StocksKeyStatisticsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stocks_key_statistics, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) =
            StocksKeyStatisticsFragment().apply {
                arguments = bundle
            }
    }
}