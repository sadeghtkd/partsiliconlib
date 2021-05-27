package com.partsilicon.partsiliconlib.dialog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.partsilicon.partsiliconlib.R
import com.partsilicon.partsiliconlib.dialog.pojo.DialogRes
import com.partsilicon.partsiliconlib.dialog.webservice.DialogWebservices
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback
import com.partsilicon.partsiliconlib.utils.SharedPreferencesUtility
import kotlinx.android.synthetic.main.activity_dialog.*
import retrofit2.Call
import retrofit2.Response

class DialogActivity : AppCompatActivity() {
    var isForce: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        var result : com.partsilicon.partsiliconlib.dialog.pojo.Result
        result = intent.getParcelableExtra("res")
        if(result == null)
            finish()

        tv_title.setText(result.titles.first().value)
        tv_description.setText(result.description.first().value)
        btn_action.setText(result.buttonText.first().value)
        if(result.photo.isNotEmpty()) {
            iv_photo.setImageURI(Uri.parse(result.photo.first().url))
            Glide.with(iv_photo).load(result.photo.first().url).into(iv_photo)
        }
        if (result.force) {
            setFinishOnTouchOutside(false)
            isForce = true
        }
        if (result.dialogType != 1)
            SharedPreferencesUtility(this).setDialogId(result.objectId)

        btn_action.setOnClickListener() {
            if (result.actionType == 1) {
                var inn = Intent("android.intent.action.VIEW")
                inn.setData(Uri.parse(result.targetUrl))
                startActivity(inn)
            } else if (result.actionType == 2) {
                try {
                    val c = Class.forName(result.targetUrl)
                    val intent = Intent(this, c)
                    startActivity(intent)
                } catch (ignored: ClassNotFoundException) {
                }
            }
            if (!isForce)
                finish()
        }



    }
    override fun onBackPressed() {
        if (!isForce)
            super.onBackPressed()
    }
}