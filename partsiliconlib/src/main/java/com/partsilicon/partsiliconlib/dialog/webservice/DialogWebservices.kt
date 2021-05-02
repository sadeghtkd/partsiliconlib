package com.partsilicon.partsiliconlib.dialog.webservice

import android.content.Context
import android.content.SharedPreferences
import com.partsilicon.partsiliconlib.BuildConfig
import com.partsilicon.partsiliconlib.classes.Setting
import com.partsilicon.partsiliconlib.classes.getMarketName
import com.partsilicon.partsiliconlib.dialog.pojo.DialogReq
import com.partsilicon.partsiliconlib.dialog.pojo.DialogRes
import com.partsilicon.partsiliconlib.notification.model.NotifList
import com.partsilicon.partsiliconlib.notification.webservice.APP_ID
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback
import com.partsilicon.partsiliconlib.utils.GetVersionCode
import com.partsilicon.partsiliconlib.utils.SharedPreferencesUtility
import java.util.*

// const val APP_ID = "NcRfUjXnZr4u7xA7xA" //Parse App ID

class DialogWebservices(val context: Context) {

    fun getDialogs( callback: MyCallback<DialogRes>) {
        val ws = RetrofitClientInstance.getRetrofitInstance().create(DialogServices::class.java)

       var req = DialogReq()
        req.`package` = context.packageName //"test"
        req.market =   getMarketName(context) //"gplay"
        req.version =   GetVersionCode(context).toInt() //0
        req.lastSeenId = SharedPreferencesUtility(context).getDialogId()
        req.deviceId = Setting().getIMEI(context)
        req.language = Locale.getDefault().getLanguage()

        val call = ws.getDialogs(APP_ID ,req )
        call.enqueue(callback)
    }
}