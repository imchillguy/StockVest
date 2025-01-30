package com.chillguy.stockvest.callbacks

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R

open class SwipeToDeleteCallback: ItemTouchHelper.Callback() {

    private var swipeBack = false

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipeBack = false
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val mBackground = ColorDrawable()
        mBackground.color = recyclerView.context.getColor(R.color.claret_50)
        mBackground.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
        mBackground.draw(c)
        val rectF = RectF(itemView.right - 50f, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 35f
        paint.typeface = ResourcesCompat.getFont(recyclerView.context, R.font.inter_semi_bold)
        val textWidth = paint.measureText("DELETE");
        c.drawText("DELETE", rectF.centerX() - (textWidth/2) - 80 , rectF.centerY() + 10, paint)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}