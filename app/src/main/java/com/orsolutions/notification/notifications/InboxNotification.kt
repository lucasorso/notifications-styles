package com.orsolutions.notification.notifications

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import javax.inject.Inject

class InboxNotification @Inject constructor(private val context: Context) : NotificationDefault(context) {

    override fun build(title: String, message: String): Notification = NotificationCompat.Builder(context, CURRENT_CHANNEL_ID)
        .setSmallIcon(CURRENT_ICON)
        .setContentTitle("$title - Inbox")
        .setContentText("$message - Inbox")
        .setStyle(
            NotificationCompat.InboxStyle()
                .addLine("Line 1")
                .addLine("Line 2")
                .addLine("Line 3")
                .addLine("Line 4")
                .addLine("Line 5")
                .addLine("Line 6")
                .addLine("Line 7")
                .setBigContentTitle("Content For Inbox")
                .setSummaryText("Summary For Inbox")
        )
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(defaultContentIntent)
        .build()

}