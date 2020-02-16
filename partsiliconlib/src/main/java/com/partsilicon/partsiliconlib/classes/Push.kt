package com.partsilicon.partsiliconlib.classes

import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging

class Push{
    /**
     * Send Data Push to Every App by its main package name
     */
    fun subscribeToPush(context: Context){
        FirebaseMessaging.getInstance().subscribeToTopic(context.packageName)
    }
}