package com.chillguy.stockvest.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chillguy.stockvest.R
import com.chillguy.stockvest.databinding.FragmentEnterPhoneNumberBinding
import com.chillguy.stockvest.enums.FragmentTransactionType
import com.chillguy.stockvest.extension.ViewExtension.setEmptyHyperTextWatcher
import com.chillguy.stockvest.listener.INavigationListener
import com.chillguy.stockvest.model.FragmentTransactionModel
import com.chillguy.stockvest.viewmodel.RegistrationViewModel

class EnterPhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentEnterPhoneNumberBinding
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var navigationListener: INavigationListener

    companion object {
        const val TAG = "EnterPhoneNumberFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationListener = context as INavigationListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_enter_phone_number, container, false)
        binding.registrationViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            backIv.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
            phoneNumberTl.setEmptyHyperTextWatcher(phoneNumberEt)
            validateBtn.setOnClickListener {
                if (!viewModel.isValidPhoneNumber()) {
                    phoneNumberTl.helperText = requireContext().getString(R.string.phone_number_incorrect)
                    return@setOnClickListener
                }
                val validatePhoneNumberFragmentModel = FragmentTransactionModel(
                    fragment = ValidatePhoneNumberFragment::class.java,
                    fragmentTransactionType = FragmentTransactionType.ADD,
                    addToBackStack = true,
                    tag = ValidatePhoneNumberFragment.TAG
                )
                activity?.let { mActivity -> viewModel.initPhoneAuthOption(mActivity) }
                navigationListener.navigateToFragment(validatePhoneNumberFragmentModel)
            }

        }
    }

}