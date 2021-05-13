package com.orsolutions.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.orsolutions.notification.di.ApplicationComponent
import com.orsolutions.notification.extensions.notificationManager

class NotificationApplication : Application() {

    val daggerComponent: ApplicationComponent by lazy { ApplicationComponent.create(this) }

    override fun onCreate() {
        super.onCreate()
        ApplicationComponent.create(this)
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel1 = NotificationChannel(
                CHANNEL_1_ID,
                CHANNEL_1,
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = "This is Channel 1" }

            val notificationChannel2 = NotificationChannel(
                CHANNEL_2_ID,
                CHANNEL_2,
                NotificationManager.IMPORTANCE_LOW
            ).apply { description = "This is Channel 2" }

            notificationManager.createNotificationChannels(listOf(notificationChannel1, notificationChannel2))
        }
    }

    companion object {

        const val CHANNEL_1 = "Channel 1"
        const val CHANNEL_1_ID = "${CHANNEL_1}_ID"

        const val CHANNEL_2 = "Channel 2"
        const val CHANNEL_2_ID = "${CHANNEL_2}_ID"
    }
}

