package com.example.lab12

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        startBackgroundTask()
    }

    private fun startBackgroundTask() {
        Thread {
            try {
                Thread.sleep(3000) // 延遲三秒
                startSecActivity()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun startSecActivity() {
        val intent = Intent(this, SecActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK // 設置標誌
        }
        startActivity(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY // 結束後不會重啟
    }

    override fun onBind(intent: Intent): IBinder? = null // 不需要綁定，返回 null
}
