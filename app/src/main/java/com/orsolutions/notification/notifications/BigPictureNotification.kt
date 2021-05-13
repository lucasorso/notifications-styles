package com.orsolutions.notification.notifications

import android.app.Notification
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.orsolutions.notification.R
import javax.inject.Inject

class BigPictureNotification @Inject constructor(private val context: Context) : NotificationDefault(context) {

    private val photo get() = BitmapFactory.decodeResource(context.resources, R.drawable.zugpsitze_mountain)

    override fun build(title: String, message: String): Notification = NotificationCompat.Builder(context, CURRENT_CHANNEL_ID)
        .setSmallIcon(CURRENT_ICON)
        .setContentTitle("$title - Big Picture")
        .setContentText("$message - Big Picture")
        .setLargeIcon(photo)
        .setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(photo)
                .setBigContentTitle("Title For Big Picture")
                .bigLargeIcon(null)
        )
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(defaultContentIntent)
        .build()
}