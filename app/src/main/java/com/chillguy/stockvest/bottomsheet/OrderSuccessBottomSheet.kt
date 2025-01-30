package com.chillguy.stockvest.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chillguy.stockvest.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderSuccessBottomSheet: BottomSheetDialogFragment() {

    companion object {
        const val TAG = "OrderSuccessBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_sucess_bottom_sheet_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.done_btn).setOnClickListener {
            dismiss()
        }
    }

}