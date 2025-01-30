package com.chillguy.stockvest.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.chillguy.stockvest.R
import com.chillguy.stockvest.databinding.FragmentRegistrationSuccessBinding
import com.chillguy.stockvest.enums.FragmentTransactionType
import com.chillguy.stockvest.listener.INavigationListener
import com.chillguy.stockvest.model.FragmentTransactionModel

class RegistrationSuccessFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationSuccessBinding
    private lateinit var navigationListener: INavigationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationListener = context as INavigationListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration_success, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            backIv.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }

            loginBtn.setOnClickListener {
                val loginFragmentModel = FragmentTransactionModel(
                    fragment = LoginFragment::class.java,
                    fragmentTransactionType = FragmentTransactionType.REPLACE
                )
                navigationListener.navigateToFragment(loginFragmentModel)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(enabled = true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })
    }

}