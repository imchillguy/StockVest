package com.chillguy.stockvest.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chillguy.stockvest.R
import com.chillguy.stockvest.databinding.FragmentLoginBinding
import com.chillguy.stockvest.enums.FragmentTransactionType
import com.chillguy.stockvest.extension.TextInputPasswordTransformation
import com.chillguy.stockvest.extension.ViewExtension.hide
import com.chillguy.stockvest.extension.ViewExtension.setEmptyHyperTextWatcher
import com.chillguy.stockvest.extension.ViewExtension.show
import com.chillguy.stockvest.listener.INavigationListener
import com.chillguy.stockvest.model.FragmentTransactionModel
import com.chillguy.stockvest.model.response.LoginResponse
import com.chillguy.stockvest.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val passwordTextInputPasswordTransformation: TextInputPasswordTransformation by lazy { TextInputPasswordTransformation(requireContext()) }
    private lateinit var navigationListener: INavigationListener
    private lateinit var viewModel: LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationListener = context as INavigationListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.loginViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            passwordTextInputPasswordTransformation.setPasswordTransformation(passwordTl, passwordEt)
            registrationBtn.setOnClickListener {
                val signUpFragmentModel = FragmentTransactionModel(
                    fragment = SignUpFragment::class.java
                )
                navigationListener.navigateToFragment(signUpFragmentModel)
            }
            forgotPasswordTv.setOnClickListener {
                val forgotPasswordFragmentModel = FragmentTransactionModel(
                    fragment = ForgotPasswordFragment::class.java,
                    addToBackStack = true,
                    tag = ForgotPasswordFragment.TAG
                )
                navigationListener.navigateToFragment(forgotPasswordFragmentModel)
            }
            emailTl.setEmptyHyperTextWatcher(emailEt)
            loginBtn.setOnClickListener {
                if (!viewModel.isValidEmail()) {
                    emailTl.helperText = requireContext().getString(R.string.email_address_incorrect)
                    return@setOnClickListener
                }
                viewModel.loginUser()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(enabled = true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is LoginResponse.Loading -> {
                    binding.progressBarLayout.show()
                }
                is LoginResponse.Success -> {
                    binding.progressBarLayout.hide()
                    val mainFragmentModel = FragmentTransactionModel(
                        fragment = MainFragment::class.java,
                        fragmentTransactionType = FragmentTransactionType.REPLACE
                    )
                    navigationListener.navigateToFragment(mainFragmentModel)
                }
                is LoginResponse.Error -> {
                    binding.progressBarLayout.hide()
                    Toast.makeText(requireContext(), response.errorMsg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}