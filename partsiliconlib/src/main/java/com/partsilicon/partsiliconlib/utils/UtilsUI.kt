package com.partsilicon.partsiliconlib.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.RemoteViews
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.NotificationTarget
import com.partsilicon.partsiliconlib.R
import com.partsilicon.partsiliconlib.notification.NotifListActivity
import com.partsilicon.partsiliconlib.notification.model.Notif
//import com.squareup.picasso.Picasso


/**
 * Display unread notification count in actionbar
 */
fun setUnreadActionMenu(context: Context, view: View) {
    val unreadCount = SharedPreferencesUtility(context).getUnreadCount()
    if (unreadCount > 0)
        view.findViewById<TextView>(R.id.tvUnreadCount).setText(unreadCount.toString())
    else
        view.findViewById<TextView>(R.id.tvUnreadCount).visibility = View.GONE
    view.setOnClickListener {
        context.startActivity(Intent(context, NotifListActivity::class.java))
    }
}

fun showNotification(
        context: Context,
        ChannelId: String,
        ChannelName: String,
        title: String,
        text: String,
        info: String,
        intent: PendingIntent?
) {
    val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val notificationChannel =
                NotificationChannel(ChannelId, ChannelName, NotificationManager.IMPORTANCE_DEFAULT)
        // Configure the notification channel.
        notificationChannel.description = ""
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.YELLOW
        //notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
        //notificationChannel.enableVibration(true);
        notificationManager.createNotificationChannel(notificationChannel)
    }
    val notificationBuilder = NotificationCompat.Builder(context, ChannelId)
    notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.info)
            .setTicker(text)
            //.setPriority(Notification.PRIORITY_MAX)
            .setContentTitle(title)
            .setContentText(text)
            .setContentInfo(info)
    if (intent != null)
        notificationBuilder.setContentIntent(intent)
    notificationManager.notify(1, notificationBuilder.build())
}

fun showCustomNotification(context: Context, notif: Notif) {

    val resultIntent = getItemActionIntent(context, notif)
    val resultPendingIntent =
            if (resultIntent != null)
                PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            else
                null

    val channelId = "NotifAlert" + context.packageName
    val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val notificationChannel =
                NotificationChannel(channelId, "Notifications", NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.description = ""
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.YELLOW
        notificationManager.createNotificationChannel(notificationChannel)
    }
    val notificationView = RemoteViews(context.packageName , R.layout.notification_view)
    notificationView.setTextViewText(R.id.tvTitle , notif.title)
    notificationView.setTextViewText(R.id.tvText , notif.shortDesc)


    //val notificationBigView = RemoteViews(context.packageName , R.layout.notification_big_view)

    val notificationBuilder = NotificationCompat.Builder(context, channelId)
    notificationBuilder.setAutoCancel(true)
            ///.setDefaults(Notification.DEFAULT_ALL)   // commented to check
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.info)
            .setTicker(notif.shortDesc)
            .setContentTitle(notif.title)
            .setContentText(notif.shortDesc)
            .setCustomContentView(notificationView)
    //if(notif.displayType ==  DisplayTypes.Big.value)
            //notificationBuilder.setCustomBigContentView(notificationBigView)
    if (resultPendingIntent != null)
        notificationBuilder.setContentIntent(resultPendingIntent)
    val notifId = notif.objectId.hashCode()

    /*if(notif.displayType ==  DisplayTypes.Big.value)
        Picasso.get().load(notif.picUrl).into(object :Target{
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                notificationBuilder.setStyle(NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap))
                notification = notificationBuilder.build()
                notificationManager.notify(notifId, notification)
            }
        })
    else*/
    var notification = notificationBuilder.build()
        notificationManager.notify(notifId, notification)
    var notifTarget = NotificationTarget(context,R.id.ivIcon,notificationView,notification,notifId)
    Glide.with(context).asBitmap().load(notif.picUrl).into(notifTarget)
    //Picasso.get().load(notif.picUrl).into(notificationView,R.id.ivIcon , notifId, notification)

        //Picasso.get().load(notif.picUrl).into(notificationBigView,R.id.ivBigPicture , notifId, notification)
}

fun showUrlNotification(context: Context, url: String, title: String, text: String, info: String) {
    val resultIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    val resultPendingIntent =
            PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    showNotification(
            context,
            "SiliconAlert" + context.packageName,
            "Silicon Alert",
            title,
            text,
            info,
            resultPendingIntent
    )
}