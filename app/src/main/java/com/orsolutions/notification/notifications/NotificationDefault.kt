package com.orsolutions.notification.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.orsolutions.notification.MainActivity
import com.orsolutions.notification.NotificationApplication
import com.orsolutions.notification.R
import com.orsolutions.notification.extensions.notificationManager
import kotlin.random.Random

abstract class NotificationDefault(private val context: Context) {

    private val activityIntent: Intent
        get() = Intent(context, MainActivity::class.java)

    protected val defaultContentIntent: PendingIntent
        get() = PendingIntent.getActivity(context, 0, activityIntent, 0)

    protected open val notificationId: Int
        get() {
            return if (RANDOM_ID) Random.nextInt() else CURRENT_NOTIFICATION_ID
        }

    abstract fun build(title: String, message: String): Notification

    open fun send(notificationTitle: String = "", notificationMessage: String = "") {
        context.notificationManager.notify(notificationId, build(notificationTitle, notificationMessage))
    }

    /**
     * Only for test purpose!
     */
    companion object {
        var CURRENT_NOTIFICATION_ID: Int = 1234
        var RANDOM_ID: Boolean = false
        var CURRENT_CHANNEL_ID: String = NotificationApplication.CHANNEL_1_ID
        var CURRENT_ICON: Int = R.drawable.ic_looks_one
    }
}