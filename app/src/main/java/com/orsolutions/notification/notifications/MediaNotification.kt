package com.orsolutions.notification.notifications

import android.app.Notification
import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import com.orsolutions.notification.R
import javax.inject.Inject

class MediaNotification @Inject constructor(private val context: Context) : NotificationDefault(context) {

    private val photo get() = BitmapFactory.decodeResource(context.resources, R.drawable.zugpsitze_mountain)

    private val mediaSession get() = MediaSessionCompat(context, "Media Session")

    override fun build(title: String, message: String): Notification = NotificationCompat.Builder(context, CURRENT_CHANNEL_ID)
        .setSmallIcon(CURRENT_ICON)
        .setContentTitle("$title - Media")
        .setContentText("$message - Media")
        .setLargeIcon(photo)
        .addAction(R.drawable.ic_dislike, "Dislike", null)
        .addAction(R.drawable.ic_previous, "Previous", null)
        .addAction(R.drawable.ic_pause, "Pause", null)
        .addAction(R.drawable.ic_next, "Next", null)
        .addAction(R.drawable.ic_like, "Like", null)
        .setStyle(
            androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(1, 2, 3)
                .setShowCancelButton(true)
                .setMediaSession(mediaSession.sessionToken)
        )
        .setSubText("Sub Text")
        .setPriority(NotificationCompat.PRIORITY_LOW)
        .setContentIntent(defaultContentIntent)
        .build()
}