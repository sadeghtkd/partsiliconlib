package com.partsilicon.partsiliconlib.notification.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.partsilicon.partsiliconlib.notification.model.Notif

@Dao
interface NotifDao {
    @Query("SELECT * FROM notif")
    fun getAll(): List<Notif>



    @Query("SELECT * FROM notif WHERE objectId = :objectId")
    fun loadById(objectId: String): Notif

    @Insert
    fun insert(notif: Notif)

    @Delete
    fun delete(notif: Notif)
}
