package com.chillguy.stockvest.bottomsheet

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chillguy.stockvest.R
import com.chillguy.stockvest.constants.OrderConfirmationConstants
import com.chillguy.stockvest.databinding.StocksBuyBottomsheetViewBinding
import com.chillguy.stockvest.extension.StringExtension.toEditable
import com.chillguy.stockvest.listener.IShowBottomSheet
import com.chillguy.stockvest.repository.StocksBuyRepository
import com.chillguy.stockvest.viewmodel.bottomsheet.StocksBuyViewModel
import com.chillguy.stockvest.viewmodel.factory.VIewModelFactory.createFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.math.max

class StocksBuyBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: StocksBuyBottomsheetViewBinding
    private lateinit var viewModel: StocksBuyViewModel
    private lateinit var showBottomSheetListener: IShowBottomSheet
    private var is25Selected = false
    private var is50Selected = false
    private var is75Selected = false
    private var is100Selected = false


    companion object {
        const val TAG = "StocksBuyBottomSheet"
    }

    fun initListener(listener: IShowBottomSheet) {
        showBottomSheetListener = listener
    }

    private val buyButtonClickListener = View.OnClickListener {
        val orderConfirmationDetails = viewModel.getOrderConfirmationDetails()
        val bundle = bundleOf(OrderConfirmationConstants.ORDER_CONFIRMATION_DETAILS to orderConfirmationDetails)
        val orderConfirmationBottomSheet = OrderConfirmationBottomSheet.getInstance(bundle)
        orderConfirmationBottomSheet.initListener(showBottomSheetListener)
        showBottomSheetListener.showBottomSheet(orderConfirmationBottomSheet, OrderConfirmationBottomSheet.TAG)
        dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = StocksBuyViewModel(
            stocksBuyRepository = StocksBuyRepository()
        ).createFactory()
        viewModel = ViewModelProvider(this, factory)[StocksBuyViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.stocks_buy_bottomsheet_view, container,false)
        return binding.root
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buyLotTv.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(editable: Editable?) {
                    viewModel.setStocksBuyOrderLot(editable?.toString())
                    viewModel.setButtonStateAndInvestmentFee()
                }
            })
            buyPriceTv.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(editable: Editable?) {
                    viewModel.setStocksBuyPrice(editable?.toString())
                    viewModel.setButtonStateAndInvestmentFee()
                }
            })
            lotMinusTv.setOnClickListener {
                val buyLot = buyLotTv.text.toString()
                if (buyLot.isNotEmpty()) {
                    buyLotTv.text = max(buyLot.toInt() - 1, 0).toString().toEditable()
                }
            }
            lotPlusTv.setOnClickListener {
                val buyLot = buyLotTv.text.toString()
                if (buyLot.isNotEmpty()) {
                    buyLotTv.text = (buyLot.toInt() + 1).toString().toEditable()
                }
            }
            priceMinusTv.setOnClickListener {
                val buyPrice = buyPriceTv.text.toString()
                if (buyPrice.isNotEmpty()) {
                    buyPriceTv.text = String.format("%.4f", max(buyPrice.toDouble() - 1.0, 0.0)).toEditable()
                }
            }
            pricePlusTv.setOnClickListener {
                val buyPrice = buyPriceTv.text.toString()
                if (buyPrice.isNotEmpty()) {
                    buyPriceTv.text = String.format("%.4f",buyPrice.toDouble() + 1.0).toEditable()
                }
            }
            buyingPower25.setOnClickListener {
                if (is25Selected) {
                    buyingPower25.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                } else {
                    buyingPower25.background = ContextCompat.getDrawable(requireContext(), R.drawable.filled_round_rect_khakhi_green_50_height_1)
                    buyingPower50.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    buyingPower75.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    buyingPower100.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    is50Selected = false
                    is75Selected = false
                    is100Selected = false
                    viewModel.stocksBuyingPowerWithPercentage(0.25)
                }
                is25Selected = !is25Selected
            }
            buyingPower50.setOnClickListener {
                if (is50Selected) {
                    buyingPower50.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                } else {
                    buyingPower25.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    buyingPower50.background = ContextCompat.getDrawable(requireContext(), R.drawable.filled_round_rect_khakhi_green_50_height_1)
                    buyingPower75.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    buyingPower100.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    viewModel.stocksBuyingPowerWithPercentage(0.5)
                    is25Selected = false
                    is75Selected = false
                    is100Selected = false
                }
                is50Selected = !is50Selected
            }
            buyingPower75.setOnClickListener {
                if (is75Selected) {
                    buyingPower75.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                } else {
                    buyingPower25.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    buyingPower50.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    buyingPower75.background = ContextCompat.getDrawable(requireContext(), R.drawable.filled_round_rect_khakhi_green_50_height_1)
                    buyingPower100.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    is25Selected = false
                    is50Selected = false
                    is100Selected = false
                    viewModel.stocksBuyingPowerWithPercentage(0.75)
                }
                is75Selected = !is75Selected
            }
            buyingPower100.setOnClickListener {
                if (is100Selected) {
                    buyingPower100.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                } else {
                    buyingPower25.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    buyingPower50.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    buyingPower75.background = ContextCompat.getDrawable(requireContext(), R.drawable.outlined_round_rect_black_50)
                    buyingPower100.background = ContextCompat.getDrawable(requireContext(), R.drawable.filled_round_rect_khakhi_green_50_height_1)
                    is25Selected = false
                    is50Selected = false
                    is75Selected = false
                    viewModel.stocksBuyingPowerWithPercentage(1.0)
                }
                is100Selected = !is100Selected
            }
            closeBtn.setOnClickListener {
                dismiss()
            }
        }
        observeLiveData()
    }

    @SuppressLint("SetTextI18n")
    private fun observeLiveData() {
        viewModel.stocksBuyingPrice.observe(viewLifecycleOwner, Observer { list ->
            binding.buyPriceTableView.bindData(list)
        })
        viewModel.stocksMarketPrice.observe(viewLifecycleOwner, Observer { value ->
            binding.buyPriceTv.text = value.toEditable()
        })
        viewModel.stocksBuyOrderLotInPercentage.observe(viewLifecycleOwner, Observer { value ->
            binding.buyLotTv.text = value.toString().toEditable()
        })
        viewModel.stocksBuyInvestmentAndFee.observe(viewLifecycleOwner, Observer { value ->
            binding.feeAmountTv.text = "$${value.toString()}"
        })
        viewModel.buyButtonEnable.observe(viewLifecycleOwner, Observer { value ->
            if (value == true) {
                binding.stocksBuyBtn.setOnClickListener(buyButtonClickListener)
                binding.stocksBuyBtn.background = ContextCompat.getDrawable(binding.root.context, R.drawable.filled_round_rect_khaki_green_50)
            } else {
                binding.stocksBuyBtn.setOnClickListener(null)
                binding.stocksBuyBtn.background = ContextCompat.getDrawable(binding.root.context, R.drawable.filled_round_rect_khaki_green_50_alpha_70)
            }
        })
    }

}