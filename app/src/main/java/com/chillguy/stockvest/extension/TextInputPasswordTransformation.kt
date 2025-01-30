package com.chillguy.stockvest.extension

import android.content.Context
import androidx.core.content.ContextCompat
import com.chillguy.stockvest.R
import com.chillguy.stockvest.extension.ViewExtension.setPasswordSpaceWatcher
import com.chillguy.stockvest.transform.BiggerDotPasswordTransformationMethod
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TextInputPasswordTransformation(private val mContext: Context) {
    private var passwordTextVisible: Boolean = false

    fun setPasswordTransformation(textInputLayout: TextInputLayout, textInputEditText: TextInputEditText) {
        with(textInputEditText) {
            transformationMethod = BiggerDotPasswordTransformationMethod
            setPasswordSpaceWatcher()
            textInputLayout.setEndIconOnClickListener {
                val selectionIndex = selectionEnd
                passwordTextVisible = !passwordTextVisible
                if (passwordTextVisible) {
                    transformationMethod = null
                    letterSpacing = 0.0f
                    textInputLayout.endIconDrawable = ContextCompat.getDrawable(mContext, R.drawable.password_hide_icon)
                } else {
                    transformationMethod = BiggerDotPasswordTransformationMethod
                    letterSpacing = 0.2f
                    textInputLayout.endIconDrawable = ContextCompat.getDrawable(mContext, R.drawable.password_show_icon)
                }
                if (selectionIndex >= 0) {
                    setSelection(selectionIndex)
                }
            }
        }
    }

}