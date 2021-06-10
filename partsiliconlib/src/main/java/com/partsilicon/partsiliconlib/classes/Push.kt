package com.partsilicon.partsiliconlib.classes

import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging
import com.partsilicon.partsiliconlib.BuildConfig
import com.partsilicon.partsiliconlib.utils.GetVersionCode

class Push{
    /**
     * Send Data Push to Every App by its main package name
     */
    fun subscribeToPush(context: Context){
        FirebaseMessaging.getInstance().subscribeToTopic(context.packageName+"_"+getMarketName(context))
        FirebaseMessaging.getInstance().unsubscribeFromTopic("v" + (GetVersionCode(context) -1).toString())
        FirebaseMessaging.getInstance().subscribeToTopic("v" + GetVersionCode(context).toString())
    }
}