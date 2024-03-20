package com.example.flexpay.utils

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage

class NotificationUtils {
    companion object {
        public fun sendSuccessNotification(token: String) {
            val data = mapOf("message" to "Your card is unblocked")
            val message = RemoteMessage.Builder(token)
                .setMessageId("1")
                .setData(data)
                .build()

            FirebaseMessaging.getInstance().send(message)
        }

        public fun sendFailureNotification(token: String) {
            val data = mapOf("message" to "Your card is blocked")
            val message = RemoteMessage.Builder(token)
                .setMessageId("2")
                .setData(data)
                .build()

            FirebaseMessaging.getInstance().send(message)
        }

    }

}
