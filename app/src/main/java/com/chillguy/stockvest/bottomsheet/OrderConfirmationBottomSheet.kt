package com.chillguy.stockvest.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.chillguy.stockvest.R
import com.chillguy.stockvest.constants.OrderConfirmationConstants
import com.chillguy.stockvest.databinding.OrderConfirmationViewBinding
import com.chillguy.stockvest.listener.IShowBottomSheet
import com.chillguy.stockvest.model.OrderConfirmationDetails
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderConfirmationBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: OrderConfirmationViewBinding
    private lateinit var orderConfirmationDetails: OrderConfirmationDetails
    private lateinit var listener: IShowBottomSheet

    companion object {
        const val TAG = "OrderConfirmationBottomSheet"
        fun getInstance(bundle: Bundle): OrderConfirmationBottomSheet {
            return OrderConfirmationBottomSheet().apply {
                arguments = bundle
            }
        }
    }

    fun initListener(listener: IShowBottomSheet) {
        this.listener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderConfirmationDetails = arguments?.get(OrderConfirmationConstants.ORDER_CONFIRMATION_DETAILS) as OrderConfirmationDetails
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_confirmation_view, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            orderConfirmationTv.text = orderConfirmationDetails.title
            Glide.with(root.context)
                .load(orderConfirmationDetails.stocksImageUrl)
                .centerCrop()
                .into(stocksIconIv)

            closeBtn.setOnClickListener { dismiss() }
            cancelBtn.setOnClickListener { dismiss() }
            confirmButtonBtn.setOnClickListener {
                listener.showBottomSheet(OrderSuccessBottomSheet(), OrderSuccessBottomSheet.TAG)
                dismiss()
            }
        }
    }

}