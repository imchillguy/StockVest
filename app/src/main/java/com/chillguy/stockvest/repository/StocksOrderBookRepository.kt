package com.chillguy.stockvest.repository

import com.chillguy.stockvest.constants.ColorConstants
import com.chillguy.stockvest.enums.StocksPriceLotType
import com.chillguy.stockvest.model.StocksPriceLotData
import com.chillguy.stockvest.model.TableData

class StocksOrderBookRepository(
    val stocksSymbol: String
) {

    fun getPriceLotData(): List<StocksPriceLotData> {
        val stocksPriceLotData = listOf(
            StocksPriceLotData("Open", "7.091,76", StocksPriceLotType.BULLISH),
            StocksPriceLotData("Lot", "186,26M"),
            StocksPriceLotData("High", "7.100,81", StocksPriceLotType.BULLISH),
            StocksPriceLotData("Val", "9,88T"),
            StocksPriceLotData("Low", "7.016,70", StocksPriceLotType.BEARISH),
            StocksPriceLotData("Avg", "1,10M"),
            StocksPriceLotData("F Buy", "3.79B"),
            StocksPriceLotData("F Sell", "18.50B")
        )
        return stocksPriceLotData
    }

    fun getBidAskLotData(): List<TableData> {
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