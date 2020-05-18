package com.partsilicon.partsiliconlib.notification.webservice

import android.content.Context
import com.partsilicon.partsiliconlib.classes.getMarketName
import com.partsilicon.partsiliconlib.notification.model.NotifList

const val APP_ID = "NcRfUjXnZr4u7xA7xA" //Parse App ID

class NotifWebservices(val context: Context) {

    fun getNotifications( callback:MyCallback<NotifList>) {
        val ws = RetrofitClientInstance.getRetrofitInstance().create(NotifServices::class.java)

        val call = ws.getNotifications(APP_ID,"{\"targetApps\":\"${context.packageName}_${getMarketName(context)}\"}")
        call.enqueue(callback)
    }
}