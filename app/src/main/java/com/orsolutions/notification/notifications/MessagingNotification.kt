package com.orsolutions.notification.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import com.orsolutions.notification.NotificationReceiver
import com.orsolutions.notification.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessagingNotification @Inject constructor(private val context: Context) : NotificationDefault(context) {

    private val remoteInput = RemoteInput.Builder("key_text_reply")
        .setLabel("Your answer...")
        .build()

    private val replyIntent = Intent(context, NotificationReceiver::class.java)

    private val replyPendingIntent = PendingIntent.getBroadcast(context, 0, replyIntent, 0)

    private val replyAction = NotificationCompat.Action
        .Builder(
            R.drawable.ic_send,
            "Reply",
            replyPendingIntent
        )
        .addRemoteInput(remoteInput)
        .build()

    private val messageStyle = NotificationCompat.MessagingStyle(
        Person.Builder()
            .setName("Me")
            .build()
    ).setConversationTitle("Group Chat")

    override fun build(title: String, message: String): Notification {
        messageStyle.messages.clear().also { MESSAGES.map { it.toNotificationMessage() }.forEach { messageStyle.addMessage(it) } }

        return NotificationCompat.Builder(context, CURRENT_CHANNEL_ID)
            .setSmallIcon(CURRENT_ICON)
            .setStyle(messageStyle)
            .addAction(replyAction)
            .setColor(Color.MAGENTA)
            .setContentTitle("$title - Messaging ")
            .setContentText("$message - Messaging ")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(defaultContentIntent)
            .build()
    }

    fun update(replyMessage: String) {
        MESSAGES.add(Message(replyMessage))
        send()
    }

    companion object {

        private val MESSAGES = mutableListOf(
            Message(
                "Good morning!",
                Person.Builder()
                    .setName("Jhon")
                    .build()
            ),
            Message("Hello"),
            Message(
                "Hi!",
                Person.Builder()
                    .setName("Maria")
                    .build()
            )
        )

    }

    data class Message(private val message: String, private val sender: Person? = null, private val timestamp: Long = System.currentTimeMillis()) {
        fun toNotificationMessage() = NotificationCompat.MessagingStyle.Message(message, timestamp, sender)
    }

}