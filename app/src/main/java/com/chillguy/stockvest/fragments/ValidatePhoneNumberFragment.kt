package com.chillguy.stockvest.fragments

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chillguy.stockvest.R
import com.chillguy.stockvest.databinding.FragmentValidatePhoneNumberBinding
import com.chillguy.stockvest.enums.FragmentTransactionType
import com.chillguy.stockvest.extension.SpannableExtension.setClickableSpan
import com.chillguy.stockvest.extension.ViewExtension.setOtpTextWatcher
import com.chillguy.stockvest.listener.INavigationListener
import com.chillguy.stockvest.model.FragmentTransactionModel
import com.chillguy.stockvest.model.response.VerifyOTPResponse
import com.chillguy.stockvest.viewmodel.RegistrationViewModel

class ValidatePhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentValidatePhoneNumberBinding
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var navigationListener: INavigationListener

    companion object {
        const val TAG = "ValidatePhoneNumberFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationListener = context as INavigationListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
        viewModel.sendOTPOnPhoneNumber()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_validate_phone_number, container, false)
        binding.registrationViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            makeResendOrChangeNumberClickable()
            backIv.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
            otpBox1.setOtpTextWatcher(otpBox1, otpBox2)
            otpBox2.setOtpTextWatcher(otpBox1, otpBox3)
            otpBox3.setOtpTextWatcher(otpBox2, otpBox4)
            otpBox4.setOtpTextWatcher(otpBox3, otpBox4)
            verifyBtn.setOnClickListener {
                if (!viewModel.isValidOTP()) {
                    return@setOnClickListener
                }
                viewModel.verifyOTP()
            }
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.verifyOTPResponse.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is VerifyOTPResponse.Success -> {
                    val registrationSuccessFragmentModel = FragmentTransactionModel(
                        fragment = RegistrationSuccessFragment::class.java,
                        fragmentTransactionType = FragmentTransactionType.REPLACE
                    )
                    navigationListener.navigateToFragment(registrationSuccessFragmentModel)
                }
                is VerifyOTPResponse.Message -> {
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun FragmentValidatePhoneNumberBinding.makeResendOrChangeNumberClickable() {
        val resendOrChangeText = requireContext().getString(R.string.resend_or_change_number)
        val spannableString = SpannableString(resendOrChangeText)
        spannableString.setClickableSpan(
            color = requireContext().getColor(R.color.red_50),
            startIndex = 0,
            endIndex = 6
        ) {
            viewModel.resendOTPOnPhoneNumber()
        }

        spannableString.setClickableSpan(
            color = requireContext().getColor(R.color.red_50),
            startIndex = 10,
            endIndex = resendOrChangeText.length
        ) {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        with(resendOrChange) {
            movementMethod = LinkMovementMethod.getInstance()
            setText(spannableString, TextView.BufferType.SPANNABLE)
        }
    }
}