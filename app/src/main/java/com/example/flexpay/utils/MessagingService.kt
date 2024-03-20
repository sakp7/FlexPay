package com.example.flexpay.utils

import android.icu.text.CaseMap.Title
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService:FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Handle the new FCM token here
        // You can save it to your server or use it as needed
        println("New FCM Token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
    fun generateNotification(title: String,msg:String){
        
    }

}