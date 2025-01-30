package com.chillguy.stockvest.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.chillguy.stockvest.R
import com.chillguy.stockvest.databinding.StocksPriceTargetViewBinding
import com.chillguy.stockvest.model.PriceTargetData
import com.chillguy.stockvest.utils.Utils.toPx

class PriceTargetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): LinearLayout(context, attrs) {

    private lateinit var binding: StocksPriceTargetViewBinding

    init {
        binding = StocksPriceTargetViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bindData(priceTargetData: PriceTargetData) {
        with(binding) {
            val currentPriceText = "Current $${priceTargetData.currentPrice}"
            currentPriceTv.text = currentPriceText
            val targetPriceText = "Target $${priceTargetData.targetPrice}"
            targetPriceTv.text = targetPriceText
            val lowPriceText = "Low $${priceTargetData.lowPrice}"
            lowPriceTv.text = lowPriceText
            val highPriceText = "High $${priceTargetData.highPrice}"
            highPriceTv.text = highPriceText

            circleAndLineTranslation(priceTargetData)
            changeTargetPriceBackground(priceTargetData)
        }
    }

    private fun changeTargetPriceBackground(priceTargetData: PriceTargetData) {
        with(binding) {
            if (priceTargetData.targetPrice < priceTargetData.currentPrice) {
                targetPriceTv.background = ContextCompat.getDrawable(context, R.drawable.filled_rount_rect_red_50_radius_10)
                targetPriceLineView.background = ContextCompat.getDrawable(context, R.drawable.line_red_50)
                targetPriceCircleView.background = ContextCompat.getDrawable(context, R.drawable.circle_red_50)
            } else {
                targetPriceTv.background = ContextCompat.getDrawable(context, R.drawable.filled_rount_rect_green_50_radius_10)
                targetPriceLineView.background = ContextCompat.getDrawable(context, R.drawable.line_green_50)
                targetPriceCircleView.background = ContextCompat.getDrawable(context, R.drawable.circle_green_50)
            }
        }
    }

    private fun circleAndLineTranslation(priceTargetData: PriceTargetData) {
        with(priceTargetData) {

            val highLowDiff = highPrice - lowPrice
            val currentLowDiff = currentPrice - lowPrice
            val targetLowDiff = targetPrice - lowPrice

            val deviceWidth = 1f * context.resources.displayMetrics.widthPixels

            val dp4 = context.toPx(4)

            val scaleByHighLowDiff = deviceWidth / highLowDiff

            val currentTranslationX = currentLowDiff * scaleByHighLowDiff
            binding.currentPriceCircleView.translationX = currentTranslationX.toFloat()
            binding.currentPriceLineView.translationX = currentTranslationX.toFloat() + dp4

            val targetTranslationX = targetLowDiff * scaleByHighLowDiff
            binding.targetPriceCircleView.translationX = targetTranslationX.toFloat()
            binding.targetPriceLineView.translationX = targetTranslationX.toFloat() + dp4

            // text translation
            binding.currentPriceTv.post {
                val currentPriceWidth = binding.currentPriceTv.width
                val currentPriceHalfWidth = currentPriceWidth / 2
                var translationX = currentTranslationX.toFloat() + dp4 - currentPriceHalfWidth
                if (translationX < 0) {
                    translationX = 0f
                } else if (translationX > deviceWidth) {
                    translationX = deviceWidth - currentPriceWidth
                } else if (translationX + currentPriceHalfWidth > deviceWidth) {
                    translationX = deviceWidth - currentPriceHalfWidth
                }
                binding.currentPriceTv.translationX = translationX
            }

            binding.targetPriceTv.post {
                val targetPriceWidth = binding.targetPriceTv.width
                val targetPriceHalfWidth = targetPriceWidth / 2
                var translationX = targetTranslationX.toFloat() + dp4 - targetPriceHalfWidth
                if (translationX < 0) {
                    translationX = 0f
                } else if (translationX > deviceWidth) {
                    translationX = deviceWidth - targetPriceWidth
                } else if (translationX + targetPriceHalfWidth > deviceWidth) {
                    translationX = deviceWidth - targetPriceHalfWidth
                }
                binding.targetPriceTv.translationX = translationX
            }
        }
    }


}