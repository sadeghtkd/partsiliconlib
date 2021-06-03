package com.partsilicon.partsiliconlib.services


import android.os.Handler
import com.bumptech.glide.Glide

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.partsilicon.partsiliconlib.notification.db.saveNotifToDb
import com.partsilicon.partsiliconlib.notification.model.NotifList
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback
import com.partsilicon.partsiliconlib.notification.webservice.NotifWebservices
import com.partsilicon.partsiliconlib.utils.showUrlNotification
import retrofit2.Call
import retrofit2.Response


open class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {

            val notifType = remoteMessage.data["notifType"]
            if(notifType == "newAlert") {
                //دریافت اعلامیه های جدید
                NotifWebservices(this).getNotifications(object : MyCallback<NotifList>(this){
                    override fun onFailure(call: Call<NotifList>, t: Throwable) {
                    }
                    override fun onResponse(call: Call<NotifList>, response: Response<NotifList>) {
                        if(response.isSuccessful)
                        {
                            saveNotifToDb(this@MyFirebaseMessagingService.applicationContext,true, response.body()?.results!! )
                        }
                    }
                })
            }
            else if(notifType == null) {
                //old notif method
                val url = remoteMessage.data["url"]
                val title = remoteMessage.data["title"]
                val msg = remoteMessage.data["msg"]

                val image = remoteMessage.data["image"]
                val banner = remoteMessage.data["banner"]
                val backgroundColor = remoteMessage.data["backgroundColor"]
                val textColor = remoteMessage.data["textColor"]


                if (url != null) {
                    var  mainHandler:Handler = Handler(this.getMainLooper());

                    var myRunnable = object  : Runnable {
                        override fun run() {
                            showUrlNotification(this@MyFirebaseMessagingService.applicationContext, url, title
                                    ?: "", msg ?: "", "", image ?: "", banner ?: "", backgroundColor
                                    ?: "", textColor ?: "")
                        } // This is your code
                    };
                    mainHandler.post(myRunnable)
                }
            }
        }else
            super.onMessageReceived(remoteMessage)
    }
}