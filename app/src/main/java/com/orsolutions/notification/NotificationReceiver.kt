package com.orsolutions.notification

import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.orsolutions.notification.extensions.getDaggerComponent
import com.orsolutions.notification.notifications.MessagingNotification
import javax.inject.Inject

class NotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var messagingNotification: MessagingNotification

    override fun onReceive(context: Context, intent: Intent) {
        context.getDaggerComponent().inject(this)

        val message = intent.getStringExtra(EXTRA_MESSAGE) ?: ""
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

        RemoteInput.getResultsFromIntent(intent)?.let {
            val replyMessage = it.getString("key_text_reply", "")
            messagingNotification.update(replyMessage).takeIf { replyMessage.isNotEmpty() }
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }
}