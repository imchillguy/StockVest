package com.chillguy.stockvest.websockets

import com.chillguy.stockvest.listener.IStocksCurrentPrice
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class StocksPriceWebSocket(private val listener: IStocksCurrentPrice): WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        webSocket.send("{\"action\":\"subscribe\",\"params\":{\"symbols\":\"BTC/USD\"}}")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        listener.getStocksCurrentPrice(text)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
    }

}