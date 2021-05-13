package com.orsolutions.notification.extensions

import android.app.NotificationManager
import android.content.Context
import com.orsolutions.notification.NotificationApplication
import com.orsolutions.notification.di.ApplicationComponent

fun Context.getDaggerComponent(): ApplicationComponent = (applicationContext as NotificationApplication).daggerComponent

val Context.notificationManager: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager