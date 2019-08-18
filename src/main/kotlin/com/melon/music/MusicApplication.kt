package com.melon.music

import com.melon.music.handler.EchoHandler
import com.melon.music.server.SocketServer
import java.util.*

class MusicApplication {

    companion object {
        @JvmStatic
        private val inputScanner: Scanner = Scanner(System.`in`)

        @JvmStatic
        fun main(args: Array<String>) {
            startServer()
            inputScanner.close()
        }

        // 启动socketServer
        @JvmStatic
        private fun startServer() {
            val server: SocketServer = SocketServer(8080, EchoHandler())
            println("Start a Server on 8080")
            println("按任意键退出...")
            inputScanner.next()
            server.close()
        }
    }

}
