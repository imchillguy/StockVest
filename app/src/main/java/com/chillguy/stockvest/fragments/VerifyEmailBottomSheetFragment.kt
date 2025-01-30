package com.chillguy.stockvest.fragments

import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chillguy.stockvest.R
import com.chillguy.stockvest.constants.Constants
import com.chillguy.stockvest.databinding.FragmentVerifyEmailBottomSheetBinding
import com.chillguy.stockvest.viewmodel.RegistrationViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class VerifyEmailBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentVerifyEmailBottomSheetBinding
    private lateinit var viewModel: RegistrationViewModel

    companion object {
        const val TAG = "VerifyEmailBottomSheetFragment"

        private var emailAddress: String = ""

        fun getInstance(bundle: Bundle): VerifyEmailBottomSheetFragment{
            emailAddress = bundle.getString(Constants.EMAIL_ADDRESS, "")
            return VerifyEmailBottomSheetFragment()
        }
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
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_verify_email_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setStyleSpanForEmailAddress()
            resendBtn.setOnClickListener {
                viewModel.resendPasswordResetEmail(emailAddress)
            }
        }
    }


    private fun FragmentVerifyEmailBottomSheetBinding.setStyleSpanForEmailAddress() {
        val verifyEmailDescriptionText =
            requireContext().getString(R.string.verify_email_description)
                .replace("{{emailAddress}}", emailAddress)
        val spannableString = SpannableString(verifyEmailDescriptionText)
        val startIndex = verifyEmailDescriptionText.indexOf(emailAddress)
        val endIndex = startIndex + emailAddress.length
        spannableString.setSpan(
            ForegroundColorSpan(requireContext().getColor(R.color.black_80)),
            startIndex,
            endIndex,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_extra_bold)
            typeface?.let { mTypeface ->
                spannableString.setSpan(
                    TypefaceSpan(mTypeface),
                    startIndex,
                    endIndex,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
            }
        }
        verifyEmailDescriptionTv.text = spannableString
    }

}