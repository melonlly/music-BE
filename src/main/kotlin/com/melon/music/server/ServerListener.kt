package com.melon.music.server

import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import java.util.*

class ServerListener: Thread {
    private val socketServer: SocketServer
    private val serverSocket: ServerSocket
    private val connectionThreads: Vector<ConnectionThread>
    private val notRunningConnectionThreads: Vector<ConnectionThread>
    var isRunning: Boolean

    constructor(socketServer: SocketServer, serverSocket: ServerSocket) {
        this.socketServer = socketServer
        this.serverSocket = serverSocket
        this.connectionThreads = Vector<ConnectionThread>()
        this.notRunningConnectionThreads = Vector<ConnectionThread>()
        this.isRunning = true
    }

    override fun run() {
        while (isRunning) {
            if (serverSocket.isClosed) {
                isRunning = false
                break
            }

            // 移除未在运行的线程
            for (connectionThread in connectionThreads) {
                if (!connectionThread.isRunning) {
                    notRunningConnectionThreads.addElement(connectionThread)
                }
            }
            for (connectionThread in notRunningConnectionThreads) {
                connectionThreads.remove(connectionThread)
            }
            notRunningConnectionThreads.clear()

            println("当前线程connectionThread数 ====> ${connectionThreads.size}")

            try {
                val socket: Socket = serverSocket.accept()
                val connectionThread = ConnectionThread(socket, socketServer)
                connectionThreads.addElement(connectionThread)
                connectionThread.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    fun stopRunning() {
        println("已连接线程数：" + connectionThreads.size)
        if (connectionThreads.size > 0) {
            val size = connectionThreads.size - 1
            for (i in 0..size) {
                println(i)
                connectionThreads.elementAt(i).stopRunning()
            }
        }
        isRunning = false
    }

}