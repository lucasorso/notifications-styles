package com.orsolutions.notification.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import com.orsolutions.notification.NotificationReceiver
import com.orsolutions.notification.R
import javax.inject.Inject

class SimpleActionNotification @Inject constructor(private val context: Context) : NotificationDefault(context) {

    private val broadCastIntent: Intent
        get() {
            return Intent(context, NotificationReceiver::class.java)
                .apply {
                    putExtras(bundleOf(NotificationReceiver.EXTRA_MESSAGE to "My action message!"))
                }
        }

    private val actionIntent = PendingIntent.getBroadcast(context, 0, broadCastIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    override fun build(title: String, message: String): Notification = NotificationCompat.Builder(context, CURRENT_CHANNEL_ID)
        .setSmallIcon(CURRENT_ICON)
        .setContentTitle("$title - Action")
        .setContentText("$message - Action")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(defaultContentIntent)
        .setColor(Color.CYAN)
        .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
        .build()

}