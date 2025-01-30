package com.chillguy.stockvest.itemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class FilterOptionsSeparatorItemDecoration(context: Context, space: Int, @DrawableRes  dividerRes: Int): DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL) {

    private val mDivider: Drawable = ContextCompat.getDrawable(context, dividerRes)!!
    private val mSpace = space/2

    init {
        this.setDrawable(mDivider)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.right = mSpace
        } else if (position == parent.adapter?.itemCount?.minus(1)) {
            outRect.left = mSpace
        } else {
            outRect.left = mSpace
            outRect.right = mSpace
        }
    }

}