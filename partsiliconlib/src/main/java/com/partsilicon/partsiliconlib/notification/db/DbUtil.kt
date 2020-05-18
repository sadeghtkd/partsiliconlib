package com.partsilicon.partsiliconlib.notification.db

import android.content.Context
import com.partsilicon.partsiliconlib.notification.model.Notif
import com.partsilicon.partsiliconlib.utils.SharedPreferencesUtility
import com.partsilicon.partsiliconlib.utils.showCustomNotification
import com.partsilicon.partsiliconlib.utils.showUrlNotification
import me.leolin.shortcutbadger.ShortcutBadger

fun saveNotifToDb(context: Context ,fromService: Boolean , dataList:List<Notif>){
    val db = AppDatabase.getInstance(context)
    var badgeCount = 0
    dataList?.forEach {
        //if not saved before
        if( db?.notifDAO()?.loadById(it.objectId) == null) {
            db?.notifDAO()?.insert(it)
            badgeCount++
            //show notification if needed
            if(it.showNotif && fromService)
                showCustomNotification(context,it)
        }
    }
    if(badgeCount > 0 && fromService) {
        ShortcutBadger.applyCount(context, badgeCount)
        SharedPreferencesUtility(context).setUnreadCount(badgeCount)
    }
}