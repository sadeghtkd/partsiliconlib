package com.partsilicon.partsiliconlib.notification.webservice

import com.partsilicon.partsiliconlib.notification.model.NotifList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface NotifServices {

    @Headers("Content-Type:application/json")
    @GET("Notif")
    fun getNotifications( @Header("X-Parse-Application-Id") appID: String ,@Query("where")  where:String): Call<NotifList>
}