package com.chillguy.stockvest.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chillguy.stockvest.R
import com.chillguy.stockvest.databinding.FragmentSignUpBinding
import com.chillguy.stockvest.enums.FragmentTransactionType
import com.chillguy.stockvest.extension.TextInputPasswordTransformation
import com.chillguy.stockvest.extension.ViewExtension.hide
import com.chillguy.stockvest.extension.ViewExtension.setEmptyHyperTextWatcher
import com.chillguy.stockvest.extension.ViewExtension.setPasswordHelperTextWatcher
import com.chillguy.stockvest.extension.ViewExtension.show
import com.chillguy.stockvest.listener.INavigationListener
import com.chillguy.stockvest.model.FragmentTransactionModel
import com.chillguy.stockvest.model.response.SignUpResponse
import com.chillguy.stockvest.viewmodel.RegistrationViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private var passwordTextVisible: Boolean = false
    private var confirmPasswordTextVisible: Boolean = false
    private val passwordTextInputPasswordTransformation: TextInputPasswordTransformation by lazy { TextInputPasswordTransformation(requireContext()) }
    private val confirmPasswordTextInputPasswordTransformation: TextInputPasswordTransformation by lazy { TextInputPasswordTransformation(requireContext()) }
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var navigationListener: INavigationListener

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
        passwordTextVisible = false
        confirmPasswordTextVisible = false
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.registrationViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            with(passwordTl) {
                passwordTextInputPasswordTransformation.setPasswordTransformation(this, passwordEt)
                setPasswordHelperTextWatcher(passwordEt)
            }

            with(confirmPasswordTl) {
                confirmPasswordTextInputPasswordTransformation.setPasswordTransformation(this, confirmPasswordEt)
                setPasswordHelperTextWatcher(confirmPasswordEt)
            }
            emailTl.setEmptyHyperTextWatcher(emailEt)
            registrationBtn.setOnClickListener {
                if (!viewModel.isValidEmail()) {
                    emailTl.helperText = requireContext().getString(R.string.email_address_incorrect)
                    return@setOnClickListener
                }

                if (!viewModel.passwordMatches())return@setOnClickListener

                viewModel.createNewUser()
            }
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.signUpResponse.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is SignUpResponse.Loading -> {
                    binding.progressBarLayout.show()
                }
                is SignUpResponse.Success -> {
                    binding.progressBarLayout.hide()
                    val phoneNumberFragmentModel = FragmentTransactionModel(
                        fragment = EnterPhoneNumberFragment::class.java,
                        fragmentTransactionType = FragmentTransactionType.REPLACE,
                        tag = EnterPhoneNumberFragment.TAG,
                        addToBackStack = true
                    )
                    navigationListener.navigateToFragment(phoneNumberFragmentModel)
                }
                is SignUpResponse.Error -> {
                    binding.progressBarLayout.hide()
                    Toast.makeText(requireContext(), response.errorMsg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}