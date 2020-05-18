package com.partsilicon.partsiliconlib.notification.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.partsilicon.partsiliconlib.notification.model.Notif


@Database(entities = arrayOf(Notif::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notifDAO(): NotifDao?

    companion object {
        private const val DATABASE_NAME = "mynotif_db"
        private var instance: AppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().build()
            }
            return instance
        }
    }
}

