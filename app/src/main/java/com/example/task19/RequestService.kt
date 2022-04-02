package com.example.task19

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class RequestService : Service() {
    private val TAG = "RequestService"

    init {
        Log.d(TAG, "Service is running")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        setOneTimeWorkRequest()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? = null

    private fun setOneTimeWorkRequest() {
        val uploadRequest = OneTimeWorkRequestBuilder<RequestWorker>()
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueue(uploadRequest)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service is being killed")
    }
}