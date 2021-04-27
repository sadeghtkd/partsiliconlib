package com.partsilicon.partsiliconlib.dialog.webservice

import com.partsilicon.partsiliconlib.dialog.pojo.DialogReq
import com.partsilicon.partsiliconlib.dialog.pojo.DialogRes
import com.partsilicon.partsiliconlib.notification.model.NotifList
import retrofit2.Call
import retrofit2.http.*

interface DialogServices {

    @Headers("Content-Type:application/json")
    @POST("getDialogs")
    fun getDialogs(@Header("X-Parse-Application-Id") appID: String, @Body req:DialogReq ): Call<DialogRes>
}