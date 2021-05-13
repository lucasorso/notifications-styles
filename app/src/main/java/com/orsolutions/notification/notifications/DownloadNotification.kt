package com.orsolutions.notification.notifications

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*
import javax.inject.Inject

class DownloadNotification @Inject constructor(private val context: Context) : NotificationDefault(context) {

    private val mainScope: CoroutineScope = MainScope()
    private var maxProgress = 100
    private var progress = 0
    private var ongoing = true

    override fun build(title: String, message: String): Notification = NotificationCompat.Builder(context, CURRENT_CHANNEL_ID)
        .setSmallIcon(CURRENT_ICON)
        .setContentTitle("$title - Download ")
        .setContentText("$message - Download ")
        .setPriority(NotificationCompat.PRIORITY_LOW)
        .setOngoing(ongoing)
        .setOnlyAlertOnce(true)
        .setProgress(maxProgress, progress, false)
        .setContentIntent(defaultContentIntent)
        .build()

    override fun send(notificationTitle: String, notificationMessage: String) {
        simulateFakeDownload()
        super.send(notificationTitle, notificationMessage)
    }

    private fun simulateFakeDownload() {
        maxProgress = 100
        ongoing = true
        mainScope.launch(Dispatchers.Default) {
            for (i in 0..maxProgress step 10) {
                delay(1_000)
                progress = i
                super.send("Download", "Download in progress")
            }
            ongoing = false
            maxProgress = 0
            progress = 0
            super.send("Download", "Download Finished")
        }
    }

}