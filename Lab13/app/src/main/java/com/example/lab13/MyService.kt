package com.example.lab13

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    private var channel: String = ""
    private var thread: Thread? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        channel = intent?.getStringExtra("channel") ?: ""

        sendInitialBroadcast()
        restartThread()

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    private fun sendInitialBroadcast() {
        val message = when (channel) {
            "music" -> "歡迎來到音樂頻道"
            "new" -> "歡迎來到新聞頻道"
            "sport" -> "歡迎來到體育頻道"
            else -> "頻道錯誤"
        }
        sendBroadcast(message)
    }

    private fun restartThread() {
        thread?.takeIf { it.isAlive }?.interrupt()

        thread = Thread {
            try {
                Thread.sleep(1000) //////5000:調整出現的速度
                val message = when (channel) {
                    "music" -> "即將播放本月TOP10音樂"
                    "new" -> "即將為您提供獨家新聞"
                    "sport" -> "即將播報本週NBA賽事"
                    else -> "頻道錯誤"
                }
                sendBroadcast(message)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.apply { start() }
    }

    private fun sendBroadcast(msg: String) {
        sendBroadcast(Intent(channel).apply { putExtra("msg", msg) })
    }
}
