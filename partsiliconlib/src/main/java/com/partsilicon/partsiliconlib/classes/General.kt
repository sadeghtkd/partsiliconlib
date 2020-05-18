package com.partsilicon.partsiliconlib.classes

import android.content.Context
import android.content.pm.PackageManager


fun getMarketName(context:Context):String{
    val ai =context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
    return  ai.metaData.get("market")?.toString() ?: ""
}