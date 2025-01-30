package com.chillguy.stockvest.model

data class TableData(
    val id: Int,
    val tableItemList: List<TableItemData>
) {
    data class TableItemData(
        val text: String,
        val textColor: String
    )
}
