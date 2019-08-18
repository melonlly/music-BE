package com.melon.music.server

import java.io.IOException
import java.io.OutputStream
import java.net.Socket

class Connection(private val socket: Socket) {

    fun outPutMsg(message: String) {
        val outputStream: OutputStream
        try {
            outputStream = socket.getOutputStream()
            outputStream.write(message.toByteArray(charset("UTF-8")))
            outputStream.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}