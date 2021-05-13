package com.orsolutions.notification.di

import android.content.Context
import com.orsolutions.notification.MainActivity
import com.orsolutions.notification.NotificationReceiver
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: NotificationReceiver)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    companion object {

        fun create(context: Context): ApplicationComponent = DaggerApplicationComponent.factory().create(context)
    }
}