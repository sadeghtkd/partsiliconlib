package com.partsilicon.partsiliconlib.utils

import android.content.pm.PackageManager
import android.R.attr.versionName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import com.partsilicon.partsiliconlib.dialog.DialogActivity
import com.partsilicon.partsiliconlib.dialog.pojo.DialogRes
import com.partsilicon.partsiliconlib.dialog.webservice.DialogWebservices
import com.partsilicon.partsiliconlib.notification.ActionTypes
import com.partsilicon.partsiliconlib.notification.NotifListActivity
import com.partsilicon.partsiliconlib.notification.model.Notif
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback
import retrofit2.Call
import retrofit2.Response
import saman.zamani.persiandate.PersianDateFormat
import java.lang.Exception

/**
 * convert input date with yyyy-MM-dd'T'HH:mm:ss.SSSZ format to Shamsi as Y/m/d
 */
fun toShamsi( dueDate:String? ):String?
{
    val persianDateFormat = PersianDateFormat()
    var shamsi: String? = null
    try {
        val pdformater1 = PersianDateFormat("Y/m/d")
        shamsi = pdformater1.format(persianDateFormat.parseGrg(dueDate, "yyyy-MM-dd'T'HH:mm:ss.S")).toString()
        return  shamsi
    } catch (e: Exception) {
        return   e.printStackTrace().toString()
    }
}

fun GetAboutUSPage(context: Context):String{
        var url = "http://partsilicon.com/about?pck=${context.packageName}&v=${GetVersionCode(context)}"
        Log.d("lib","aboutus:$url")
        return url
    }

    fun GetVersionName(context: Context): String {
        var ver = ""
        val packName = context.getPackageName()
        try {
            ver = context.getPackageManager().getPackageInfo(packName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return ver
    }

    fun GetVersionCode(context: Context): Long {
        var verCode = 0L
        val packName = context.packageName
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                verCode = context.packageManager.getPackageInfo(packName, 0).longVersionCode
            }else
                verCode =  context.packageManager.getPackageInfo(packName, 0).versionCode.toLong()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return verCode
    }

    fun ToPersianNumbers(strLatinNum:String?):String?{
        return strLatinNum?.replace("0", "\u0660")?.replace("1", "\u0661")?.replace("2", "\u0662")?.replace("3", "\u0663")?.replace("4", "\u0664")?.replace("5", "\u0665")?.replace("6", "\u0666")?.replace("7", "\u0667")?.replace("8", "\u0668")?.replace("9", "\u0669")
    }

    fun getItemActionIntent(context: Context, item: Notif?): Intent? {
        var intent : Intent? = null
        if(item?.actionType == ActionTypes.OPEN_LINK.value){
            intent = (Intent(Intent.ACTION_VIEW , Uri.parse(item.action)))
        }else if(item?.actionType == ActionTypes.OPEN_ACTIVITY.value){
            intent = (Intent(context , context.classLoader.loadClass(item.action)))
        }else if(item?.actionType == ActionTypes.NO_ACTION.value){
            intent = (Intent(context , NotifListActivity::class.java))
        }
        return intent
    }

    fun getDialog(context: Context)
    {
        DialogWebservices(context).getDialogs(object : MyCallback<DialogRes>(context) {
            override fun onResponse(call: Call<DialogRes>, response: Response<DialogRes>) {
                super.onResponse(call, response)
                var dr = DialogRes()
                if (response.isSuccessful) {
                    dr = response.body()!!
                    if (dr.result != null) {
                       var inn =  Intent(context, DialogActivity::class.java)
                        inn.putExtra("res" , dr.result)
                        context.startActivity(inn)
                    }
                }
            }

            override fun onFailure(call: Call<DialogRes>, t: Throwable) {
                super.onFailure(call, t)
            }
        })
    }