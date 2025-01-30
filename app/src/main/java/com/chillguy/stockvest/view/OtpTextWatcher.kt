package com.chillguy.stockvest.view

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class OtpTextWatcher(private val prevEditText: EditText, private val nextEditText: EditText): TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(editable: Editable?) {
        when (editable?.length) {
            0 -> prevEditText.requestFocus()
            1 -> nextEditText.requestFocus()
            else -> {}
        }
    }


}