package com.melon.music.server

import com.melon.music.enums.HeaderEnum
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.Socket

class ConnectionThread: Thread {
    private val socket: Socket
    private val socketServer: SocketServer
    private var connection: Connection
    var isRunning: Boolean

    constructor(socket: Socket, socketServer: SocketServer) {
        this.socket = socket
        this.socketServer = socketServer
        connection = Connection(socket)
        isRunning = true
    }

    override fun run() {
        while (isRunning) {
            // 检查当前socket是否已关闭
            if (socket.isClosed) {
                stopRunning()
                break
            }
            var reader: BufferedReader
//            var inputStream: InputStream
//            val bytes: ByteArray = ByteArray(1024)
//            var len: Int = 0 // 长度
//            var sb: StringBuilder = StringBuilder()
            try {
                reader = BufferedReader(InputStreamReader(socket.getInputStream()))
                var rawMessage: String = reader.readLine()

//                inputStream = socket.getInputStream()
//                // 只有当客户端关闭它的输出流的时候，服务端才能取得结尾的-1
//                while (len != -1) {
//                    len = inputStream.read(bytes)
//                    sb.append(String(bytes, 0, len, charset("UTF-8")))
//                }
//
//                val rawMessage: String = sb.toString()

                if (rawMessage != null) {
                    socketServer.messageHandler.onReceive(connection, rawMessage)
                }

                stopRunning()

                // 判断消息头
//                when (header) {
//                    enumValueOf<HeaderEnum>("PURE").toString() -> {
//                        // 处理message
//                        if (message != null) {
//                            socketServer.messageHandler.onReceive(connection, message)
//                        }
//                    }
//                    enumValueOf<HeaderEnum>("CLOSED").toString() -> {
//                        stopRunning()
//                    }
//                }

            } catch (e: IOException) {
                e.printStackTrace()
                stopRunning()
            }
        }
    }

    // 停止运行
    fun stopRunning() {
        isRunning = false
        try {
            socket.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}