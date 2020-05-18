package com.partsilicon.partsiliconlib.notification.webservice

import android.content.Context
import android.widget.Toast
import com.partsilicon.partsiliconlib.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MyCallback<T>(private val context: Context) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        return
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
            //General.showErrorMsg(context, "Error in connection\nCheck your Internet\nPlease try later")

            Toast.makeText(context , R.string.errInConn,Toast.LENGTH_LONG).show()
        }

}