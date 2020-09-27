package com.partsilicon.partsiliconlib.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback
import com.partsilicon.partsiliconlib.pojo.App
import com.partsilicon.partsiliconlib.pojo.AppsObj
import com.partsilicon.partsiliconlib.pojo.InviteRes
import ir.partsilicon.ordermember.webservices.AppsWebService
import ir.partsilicon.ordermember.webservices.Invite

import retrofit2.Call
import retrofit2.Response

class AppsViewModel : ViewModel() {

    val AppsList = MutableLiveData <AppsObj>()
    val invited = MutableLiveData<InviteRes>()

    fun getApps(context:Context , hostApp:String , imei:String ){

        val listener = object  : MyCallback<AppsObj>(context){
            override fun onResponse(call: Call<AppsObj>, response: Response<AppsObj>) {
                if(response.isSuccessful)
                    AppsList.value = response.body()
            }
        }
        AppsWebService(context).getAppList(hostApp , imei ,  listener)
    }


}