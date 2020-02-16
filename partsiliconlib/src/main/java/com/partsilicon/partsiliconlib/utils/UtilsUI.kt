package com.partsilicon.partsiliconlib.utils

import android.app.Notification
import androidx.core.app.NotificationCompat
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.content.Intent
import android.net.Uri
import com.partsilicon.partsiliconlib.R


fun showNotification(
    context: Context,
    ChannelId: String,
    ChannelName: String,
    title: String,
    text: String,
    info: String,
    intent: PendingIntent
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
        .setContentIntent(intent)
        .setDefaults(Notification.DEFAULT_ALL)
        .setWhen(System.currentTimeMillis())
        .setSmallIcon(R.drawable.ic_notif)
        .setTicker(text)
        //.setPriority(Notification.PRIORITY_MAX)
        .setContentTitle(title)
        .setContentText(text)
        .setContentInfo(info)
    notificationManager.notify(1, notificationBuilder.build())
}

fun showUrlNotification(context: Context, url: String, title: String, text: String, info: String) {
    val resultIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    val resultPendingIntent =
        PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    showNotification(
        context,
        "SiliconAlert"+context.packageName,
        "Silicon Alert",
        title,
        text,
        info,
        resultPendingIntent
    )
}