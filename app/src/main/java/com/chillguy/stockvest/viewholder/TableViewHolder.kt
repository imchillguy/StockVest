package com.chillguy.stockvest.viewholder

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.constants.ColorConstants
import com.chillguy.stockvest.model.TableData

class TableViewHolder(private val mItemView: View): RecyclerView.ViewHolder(mItemView) {

    private lateinit var tableItemOne: TextView
    private lateinit var tableItemTwo: TextView
    private lateinit var tableItemThree: TextView
    private lateinit var tableItemFour: TextView

    init {
        with(mItemView) {
            tableItemOne = findViewById(R.id.table_row_tv_1)
            tableItemTwo = findViewById(R.id.table_row_tv_2)
            tableItemThree = findViewById(R.id.table_row_tv_3)
            tableItemFour = findViewById(R.id.table_row_tv_4)
        }
    }

    fun bindData(tableData: TableData, mPosition: Int) {
        with(tableData) {
            val fontTypeface = getFontTypeFace(position = mPosition)
            tableItemOne.setTableItemData(tableItemList, fontTypeface, 0)
            tableItemTwo.setTableItemData(tableItemList, fontTypeface,1)
            tableItemThree.setTableItemData(tableItemList, fontTypeface, 2)
            tableItemFour.setTableItemData(tableItemList, fontTypeface, 3)
        }
    }

    private fun getFontTypeFace(position: Int): Typeface? {
        return when {
            position == 0 -> {
                ResourcesCompat.getFont(mItemView.context, R.font.inter_semi_bold)
            }
            else -> {
                ResourcesCompat.getFont(mItemView.context, R.font.inter_regular)
            }
        }
    }

    private fun TextView.setTableItemData(tableRowList: List<TableData.TableItemData>,fontTypeface: Typeface?, index: Int) {
        val tableItem = getTableItem(tableRowList, index)
        with(tableItem) {
            this@setTableItemData.text = text
            this@setTableItemData.setTextColor(Color.parseColor(textColor))
            this@setTableItemData.typeface = fontTypeface
        }
    }

    private fun getTableItem(list: List<TableData.TableItemData>, index: Int): TableData.TableItemData {
        return if (index >= list.size) {
            TableData.TableItemData(text = "-", textColor = ColorConstants.COLOR_BLACK_80)
        } else {
            list[index]
        }
    }

}