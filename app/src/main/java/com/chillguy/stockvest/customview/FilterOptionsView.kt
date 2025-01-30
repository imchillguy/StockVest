package com.chillguy.stockvest.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.adapter.FilterOptionsAdapter
import com.chillguy.stockvest.customview.behaviour.FilterOptionsBehaviour
import com.chillguy.stockvest.customview.type.FilterOptionsViewType
import com.chillguy.stockvest.itemdecoration.FilterOptionsSeparatorItemDecoration
import com.chillguy.stockvest.itemdecoration.FilterOptionsSpaceItemDecoration
import com.chillguy.stockvest.listener.IFilterOptionsClickListener
import com.chillguy.stockvest.model.FilterOptionsData
import com.chillguy.stockvest.utils.Utils.toPx

class FilterOptionsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): LinearLayout(context, attrs) {

    private lateinit var filterOptionsRecyclerView: RecyclerView
    private lateinit var filterOptionsAdapter: FilterOptionsAdapter
    private lateinit var type: String
    private lateinit var filterOptionsBehaviour: FilterOptionsBehaviour

    init {
        LayoutInflater.from(context).inflate(R.layout.filter_options_view, this, true)
        context.theme.obtainStyledAttributes(attrs, R.styleable.FilterOptionsView, 0, 0).apply {
            type = getString(R.styleable.FilterOptionsView_type) ?: FilterOptionsViewType.SEPARATOR.type
        }
        filterOptionsBehaviour = getBehaviour()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    private fun getBehaviour(): FilterOptionsBehaviour {
        val space = context.toPx(12).toInt()
        return when (type) {
            FilterOptionsViewType.SEPARATOR.type -> {
                FilterOptionsBehaviour.SeparatorBehaviour(FilterOptionsSeparatorItemDecoration(context, space, R.drawable.vertical_divider_line))
            }
            FilterOptionsViewType.SPACE.type -> {
                FilterOptionsBehaviour.SpaceBehaviour(FilterOptionsSpaceItemDecoration(space = space))
            }
            else -> {
                FilterOptionsBehaviour.SpaceBehaviour(FilterOptionsSpaceItemDecoration(space = space))
            }
        }
    }

    fun bindData(listener: IFilterOptionsClickListener) {
        initFilterOptionsRecyclerView(listener)
    }

    fun updateFilteredOptionsList(filterOptionsList: List<FilterOptionsData>){
        if (::filterOptionsAdapter.isInitialized) {
            filterOptionsAdapter.filterOptionsDataList = filterOptionsList
        }
    }

    private fun initFilterOptionsRecyclerView(listener: IFilterOptionsClickListener) {
        filterOptionsRecyclerView = findViewById(R.id.filter_options_rv)
        filterOptionsAdapter = FilterOptionsAdapter(listener, type)
        with(filterOptionsRecyclerView) {
            adapter = filterOptionsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(filterOptionsBehaviour.itemDecoration)
            itemAnimator?.changeDuration = 0
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

}