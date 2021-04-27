package com.partsilicon.partsiliconlib.dialog.webservice

import android.content.Context
import com.partsilicon.partsiliconlib.BuildConfig
import com.partsilicon.partsiliconlib.classes.getMarketName
import com.partsilicon.partsiliconlib.dialog.pojo.DialogReq
import com.partsilicon.partsiliconlib.dialog.pojo.DialogRes
import com.partsilicon.partsiliconlib.notification.model.NotifList
import com.partsilicon.partsiliconlib.notification.webservice.APP_ID
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback

// const val APP_ID = "NcRfUjXnZr4u7xA7xA" //Parse App ID

class DialogWebservices(val context: Context) {

    fun getDialogs( callback: MyCallback<DialogRes>) {
        val ws = RetrofitClientInstance.getRetrofitInstance().create(DialogServices::class.java)

 /*       val call = ws.getDialogs(APP_ID,"{\"package\":\"${context.packageName}\"}" ,"{\"market\":\" ${getMarketName(context)}\"}"
                    , "{\"version\":0 }" )*/
                // \"${BuildConfig.VERSION_CODE}\"}"  )
        // val call = ws.getDialogs(APP_ID ,"{\"package\":\"test\"}" ,  "{\"market\":\" ${getMarketName(context)}\"}" , "{\"version\":0 }" )
       var req = DialogReq()
        req.`package` = "test"
        req.market = "gplay"
        req.version = 0

        val call = ws.getDialogs(APP_ID ,req )
        call.enqueue(callback)
    }
}