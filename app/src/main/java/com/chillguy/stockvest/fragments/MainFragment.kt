package com.chillguy.stockvest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chillguy.stockvest.R
import com.chillguy.stockvest.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var currentFragmentTag: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            bottomNav.itemIconTintList = null
            loadFragment(HomeFragment())
            setCurrentFragmentTag(HomeFragment.TAG)
            bottomNav.setOnItemSelectedListener { view ->
                when (view.itemId) {
                    R.id.home -> {
                        if (currentFragmentTag != HomeFragment.TAG) {
                            loadFragment(HomeFragment())
                            setCurrentFragmentTag(HomeFragment.TAG)
                        }
                        true
                    }
                    R.id.watchlist -> {
                        if (currentFragmentTag != WatchListFragment.TAG) {
                            loadFragment(WatchListFragment())
                            setCurrentFragmentTag(WatchListFragment.TAG)
                        }
                        true
                    }
                    R.id.portfolio -> {
                        if (currentFragmentTag != PortfolioFragment.TAG) {
                            loadFragment(PortfolioFragment())
                            setCurrentFragmentTag(PortfolioFragment.TAG)
                        }
                        true
                    }
                    R.id.transactions -> {
                        if (currentFragmentTag != TransactionsFragment.TAG) {
                            loadFragment(TransactionsFragment())
                            setCurrentFragmentTag(TransactionsFragment.TAG)
                        }
                        true
                    }
                    R.id.profile -> {
                        if (currentFragmentTag != ProfileFragment.TAG) {
                            loadFragment(ProfileFragment())
                            setCurrentFragmentTag(ProfileFragment.TAG)
                        }
                        true
                    }
                    else -> {
                        if (currentFragmentTag != HomeFragment.TAG) {
                            loadFragment(HomeFragment())
                            setCurrentFragmentTag(HomeFragment.TAG)
                        }
                        true
                    }
                }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container,fragment)
        transaction?.commit()
    }

    private fun setCurrentFragmentTag(tag: String) {
        currentFragmentTag = tag
    }

}