package com.chillguy.stockvest.repository

import com.chillguy.stockvest.constants.ColorConstants
import com.chillguy.stockvest.model.TableData

class StocksBuyRepository {

    fun getStocksBuyingPrice(): List<TableData> {
        val bidAskLotDataList = listOf(
            TableData(id = 0, tableItemList = listOf(
                TableData.TableItemData(text = "LOT", textColor = ColorConstants.COLOR_BLACK_80),
                TableData.TableItemData(text = "BID", textColor = ColorConstants.COLOR_BLACK_80),
                TableData.TableItemData(text = "ASK", textColor = ColorConstants.COLOR_BLACK_80),
                TableData.TableItemData(text = "LOT", textColor = ColorConstants.COLOR_BLACK_80)
            )),
            TableData(id = 0, tableItemList = listOf(
                TableData.TableItemData(text = "42.956", textColor = ColorConstants.COLOR_CLARET_50),
                TableData.TableItemData(text = "555", textColor = ColorConstants.COLOR_CLARET_50),
                TableData.TableItemData(text = "560", textColor = ColorConstants.COLOR_CLARET_50),
                TableData.TableItemData(text = "51.824", textColor = ColorConstants.COLOR_CLARET_50)
            )),
            TableData(id = 0, tableItemList = listOf(
                TableData.TableItemData(text = "87.229", textColor = ColorConstants.COLOR_CLARET_50),
                TableData.TableItemData(text = "550", textColor = ColorConstants.COLOR_CLARET_50),
                TableData.TableItemData(text = "560", textColor = ColorConstants.COLOR_CLARET_50),
                TableData.TableItemData(text = "2.862", textColor = ColorConstants.COLOR_CLARET_50)
            )),
            TableData(id = 0, tableItemList = listOf(
                TableData.TableItemData(text = "135.350", textColor = ColorConstants.COLOR_CLARET_50),
                TableData.TableItemData(text = "545", textColor = ColorConstants.COLOR_CLARET_50),
                TableData.TableItemData(text = "570", textColor = ColorConstants.COLOR_BLACK_80),
                TableData.TableItemData(text = "7.068", textColor = ColorConstants.COLOR_BLACK_80)
            )),
            TableData(id = 0, tableItemList = listOf(
                TableData.TableItemData(text = "62.601", textColor = ColorConstants.COLOR_CLARET_50),
                TableData.TableItemData(text = "535", textColor = ColorConstants.COLOR_CLARET_50),
                TableData.TableItemData(text = "575", textColor = ColorConstants.COLOR_GREEN_50),
                TableData.TableItemData(text = "25.338", textColor = ColorConstants.COLOR_GREEN_50)
            )),
            TableData(id = 0, tableItemList = listOf(
                TableData.TableItemData(text = "-", textColor = ColorConstants.COLOR_BLACK_80),
                TableData.TableItemData(text = "-", textColor = ColorConstants.COLOR_BLACK_80),
                TableData.TableItemData(text = "585", textColor = ColorConstants.COLOR_GREEN_50),
                TableData.TableItemData(text = "11.720", textColor = ColorConstants.COLOR_GREEN_50)
            )),
            TableData(id = 0, tableItemList = listOf(
                TableData.TableItemData(text = "-", textColor = ColorConstants.COLOR_BLACK_80),
                TableData.TableItemData(text = "-", textColor = ColorConstants.COLOR_BLACK_80),
                TableData.TableItemData(text = "590", textColor = ColorConstants.COLOR_GREEN_50),
                TableData.TableItemData(text = "41.489", textColor = ColorConstants.COLOR_GREEN_50)
            )),
        )
        return bidAskLotDataList
    }
}