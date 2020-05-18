package com.partsilicon.partsiliconlib.utils

import android.content.pm.PackageManager
import android.R.attr.versionName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import com.partsilicon.partsiliconlib.notification.ActionTypes
import com.partsilicon.partsiliconlib.notification.model.Notif

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

    fun GetVersionCode(context: Context): Int {
        var verCode = 0
        val packName = context.packageName
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                verCode = context.packageManager.getPackageInfo(packName, 0).longVersionCode as Int
            }else
                verCode = context.packageManager.getPackageInfo(packName, 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
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
        }
        return intent
    }