package com.chillguy.stockvest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chillguy.stockvest.R
import com.chillguy.stockvest.constants.Constants
import com.chillguy.stockvest.databinding.FragmentForgotPasswordBinding
import com.chillguy.stockvest.model.response.ResetPasswordResponse
import com.chillguy.stockvest.viewmodel.RegistrationViewModel

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var viewModel: RegistrationViewModel

    companion object {
        const val TAG = "ForgotPasswordFragment"
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            backIv.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
            sendBtn.setOnClickListener {
                val emailAddress = binding.emailEt.text.toString()
                viewModel.sendPasswordResetEmail(emailAddress)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(enabled = true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.popBackStack()
            }
        })
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.resendPasswordResponse.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is ResetPasswordResponse.Success -> {
                    val emailAddress = binding.emailEt.text.toString()
                    val verifyEmailBottomSheet = VerifyEmailBottomSheetFragment.getInstance(bundleOf(Constants.EMAIL_ADDRESS to emailAddress))
                    verifyEmailBottomSheet.show(childFragmentManager, VerifyEmailBottomSheetFragment.TAG)
                }
                is ResetPasswordResponse.Error -> {
                    Toast.makeText(requireContext(), requireContext().getString(R.string.please_re_verify_email_address), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}