package com.chillguy.stockvest.customview.behaviour

import androidx.recyclerview.widget.RecyclerView.ItemDecoration

sealed class FilterOptionsBehaviour(val itemDecoration: ItemDecoration) {

    data class SeparatorBehaviour(val mItemDecoration: ItemDecoration): FilterOptionsBehaviour(mItemDecoration)

    data class SpaceBehaviour(val mItemDecoration: ItemDecoration): FilterOptionsBehaviour(mItemDecoration)

}