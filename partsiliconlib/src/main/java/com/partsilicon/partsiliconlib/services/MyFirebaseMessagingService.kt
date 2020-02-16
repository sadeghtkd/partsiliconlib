package com.partsilicon.partsiliconlib.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.partsilicon.partsiliconlib.utils.showUrlNotification


open class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            val url = remoteMessage.data["url"]
            val title = remoteMessage.data["title"]
            val msg = remoteMessage.data["msg"]
            if(url!=null )
                showUrlNotification(this, url, title ?: "", msg ?: "", "")
        }
    }
}