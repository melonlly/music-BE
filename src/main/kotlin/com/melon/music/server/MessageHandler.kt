package com.melon.music.server

interface MessageHandler {
    fun onReceive(connection: Connection, message: String)
}