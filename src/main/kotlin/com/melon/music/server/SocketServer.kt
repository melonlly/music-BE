package com.melon.music.server

import java.io.IOException
import java.net.ServerSocket

class SocketServer {
    private val serverSocket: ServerSocket
    private val serverListener: ServerListener
    val messageHandler: MessageHandler

    constructor(port: Int, handler: MessageHandler) {
        messageHandler = handler
        serverSocket = ServerSocket(port)
        serverListener = ServerListener(this, serverSocket)
        try {
            serverListener.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // 关闭连接
    fun close() {
        try {
            if (serverSocket != null && !serverSocket.isClosed) {
                serverListener.stopRunning()
                serverListener.suspend()
                serverListener.stop()

                serverSocket.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}