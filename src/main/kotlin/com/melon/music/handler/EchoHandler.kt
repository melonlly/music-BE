package com.melon.music.handler

import com.melon.music.server.Connection
import com.melon.music.server.MessageHandler

class EchoHandler: MessageHandler {

    override fun onReceive(connection: Connection, message: String) {
        println("从client获取到消息：$message")
        connection.outPutMsg(message)
    }

}