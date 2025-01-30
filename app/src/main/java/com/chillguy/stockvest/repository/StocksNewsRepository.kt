package com.chillguy.stockvest.repository

import com.chillguy.stockvest.model.NewsData

class StocksNewsRepository(
    val stocksSymbol: String
) {

    fun getStocksNewsData(): List<NewsData> {
        val stocksNewsData = listOf(
            NewsData(
                newsTitle = "It's MPPA and ACES turn to enter the ranks of stocks making big profits",
                newsTime = "5:39 AM",
                newsAuthor = "Investor Daily",
                newsImageUrl = "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737988700/news_1_eolokm.png"
            ),
            NewsData(
                newsTitle = "It's MPPA and ACES turn to enter the ranks of stocks making big profits",
                newsTime = "5:39 AM",
                newsAuthor = "Investor Daily",
                newsImageUrl = "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737988700/news_2_vcnnsg.png"
            ),
            NewsData(
                newsTitle = "It's MPPA and ACES turn to enter the ranks of stocks making big profits",
                newsTime = "5:39 AM",
                newsAuthor = "Investor Daily",
                newsImageUrl = "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737988700/news_3_x451aq.png"
            )
        )
        return stocksNewsData
    }

}