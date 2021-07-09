package com.orsolutions.notification

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.orsolutions.notification.NotificationApplication.Companion.CHANNEL_1_ID
import com.orsolutions.notification.NotificationApplication.Companion.CHANNEL_2_ID
import com.orsolutions.notification.extensions.getDaggerComponent
import com.orsolutions.notification.notifications.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val notificationTitle: String
        get() = editTextTitle.text.toString()

    private val notificationMessage: String
        get() = editTextMessage.text.toString()

    @Inject
    lateinit var simpleNotification: SimpleNotification

    @Inject
    lateinit var simpleActionNotification: SimpleActionNotification

    @Inject
    lateinit var bigTextWithImageNotification: BigTextWithImageNotification

    @Inject
    lateinit var inboxNotification: InboxNotification

    @Inject
    lateinit var bigPictureNotification: BigPictureNotification

    @Inject
    lateinit var mediaNotification: MediaNotification

    @Inject
    lateinit var messagingNotification: MessagingNotification

    @Inject
    lateinit var downloadNotification: DownloadNotification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDaggerComponent().inject(this)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val (channel, icon) = when (checkedId) {
                radioChannel1.id -> (CHANNEL_1_ID to R.drawable.ic_looks_one)
                radioChannel2.id -> (CHANNEL_2_ID to R.drawable.ic_looks_two)
                else -> throw Exception("Check your Radio button IDs!")
            }
            NotificationDefault.CURRENT_CHANNEL_ID = channel
            NotificationDefault.CURRENT_ICON = icon
        }

        editTextNotificationID.doOnTextChanged { text, _, _, _ ->
            if (editTextNotificationID.hasFocus()) {
                text?.let {
                    if (it.isNotEmpty()) {
                        NotificationDefault.CURRENT_NOTIFICATION_ID = it.toString().toInt()
                    } else {
                        editTextNotificationID.clearFocus()
                        editTextNotificationID.setText("1")
                        editTextNotificationID.requestFocus()
                    }
                }
            }
        }

        checkboxRandomNotificationID.setOnCheckedChangeListener { _, isChecked ->
            editTextNotificationID.isEnabled = !isChecked
            NotificationDefault.RANDOM_ID = isChecked
        }

        buttonSendSimpleNotification.setOnClickListener(this)
        buttonSendActionNotification.setOnClickListener(this)
        buttonSendBigTextWithImageNotification.setOnClickListener(this)
        buttonSendInboxNotification.setOnClickListener(this)
        buttonSendBigPictureNotification.setOnClickListener(this)
        buttonSendMediaNotification.setOnClickListener(this)
        buttonSendMessageNotification.setOnClickListener(this)
        buttonFakeDownloadNotification.setOnClickListener(this)

        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }
    }

    override fun onClick(v: View) {
        when (v) {
            buttonSendSimpleNotification -> simpleNotification
            buttonSendActionNotification -> simpleActionNotification
            buttonSendBigTextWithImageNotification -> bigTextWithImageNotification
            buttonSendInboxNotification -> inboxNotification
            buttonSendBigPictureNotification -> bigPictureNotification
            buttonSendMediaNotification -> mediaNotification
            buttonSendMessageNotification -> messagingNotification
            buttonFakeDownloadNotification -> downloadNotification
            else -> null
        }?.send(notificationTitle, notificationMessage)
    }

}