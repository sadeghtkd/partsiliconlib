package com.partsilicon.partsiliconlib.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.partsilicon.partsiliconlib.R
import com.partsilicon.partsiliconlib.dialog.pojo.DialogRes
import com.partsilicon.partsiliconlib.notification.model.NotifList
import com.partsilicon.partsiliconlib.dialog.webservice.DialogWebservices
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback
import com.partsilicon.partsiliconlib.notification.webservice.NotifWebservices
import kotlinx.android.synthetic.main.activity_dialog.*
import kotlinx.android.synthetic.main.notification_view.*
import retrofit2.Call
import retrofit2.Response

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        DialogWebservices(this).getDialogs(object : MyCallback<DialogRes>(this){
            override fun onResponse(call: Call<DialogRes>, response: Response<DialogRes>) {
                super.onResponse(call, response)
                if (response.isSuccessful)
                {
                    var dr : DialogRes = response.body()!!
                    tv_title.setText(dr.result.titles.first().value)
                    tv_description.setText(dr.result.description.first().value)
                    btn_action.setText(dr.result.buttonText.first().value)
                }
            }
            override fun onFailure(call: Call<DialogRes>, t: Throwable) {
                super.onFailure(call, t)
            }
        })
    }
}