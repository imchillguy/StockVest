package com.chillguy.stockvest.extension

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.chillguy.stockvest.R
import com.chillguy.stockvest.StockVestApplication
import com.chillguy.stockvest.utils.Utils.stringContainsNumber
import com.chillguy.stockvest.view.OtpTextWatcher
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object ViewExtension {

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }

    fun TextInputEditText.setPasswordSpaceWatcher() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 0) {
                    this@setPasswordSpaceWatcher.letterSpacing = 0.0f
                } else {
                    this@setPasswordSpaceWatcher.letterSpacing = 0.2f
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }
        this.addTextChangedListener(textWatcher)
    }

    fun TextInputLayout.setPasswordHelperTextWatcher(textInputEditText: TextInputEditText) {
        textInputEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                if (!editable.isNullOrEmpty() && editable.length >= 6 && editable.toString().stringContainsNumber()) {
                    helperText = ""
                } else {
                    helperText = StockVestApplication.getApplicationContext().getString(R.string.minimum_of_6_characters_with_numbers)
                }
            }

        })
    }

    fun TextInputLayout.setEmptyHyperTextWatcher(textInputEditText: TextInputEditText) {
        textInputEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                helperText = ""
            }

        })
    }

    fun EditText.setOtpTextWatcher(prevEditText: EditText, nextEditText: EditText) {
        this.addTextChangedListener(OtpTextWatcher(prevEditText, nextEditText))
    }

}