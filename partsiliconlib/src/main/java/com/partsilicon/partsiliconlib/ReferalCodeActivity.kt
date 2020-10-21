package com.partsilicon.partsiliconlib

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.partsilicon.partsiliconlib.classes.Setting
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback
import com.partsilicon.partsiliconlib.pojo.InviteRes
import com.partsilicon.partsiliconlib.utils.SharedPreferencesUtility
import com.partsilicon.partsiliconlib.viewmodels.AppsViewModel
import ir.partsilicon.ordermember.webservices.AppsWebService
import kotlinx.android.synthetic.main.activity_referal_code.*
import retrofit2.Call
import retrofit2.Response

class ReferalCodeActivity : BaseActivity() {

    var userId:String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_referal_code)

        userId = intent.getStringExtra("userId")
        if(userId.isNullOrEmpty() )
            Toast.makeText(this ,"ReferalCodeActivity: UserId is empty" , Toast.LENGTH_LONG).show()
        btn_ok.setOnClickListener(){
            //viewModel = ViewModelProviders.of (ReferalCodeActivity()).get(AppsViewModel::class.java)
            Invitation(this , txt_code.text.toString() , Setting().getIMEI(this))
        }

        btn_donthave.setOnClickListener(){
            SharedPreferencesUtility(this).setIsRefCodeEntered()
            finish()
        }
    }
    lateinit var invited : InviteRes
    fun Invitation(context: Context, code:String, imei:String ){

        val listener = object  : MyCallback<InviteRes>(context){
            override fun onResponse(call: Call<InviteRes>, response: Response<InviteRes>) {
                if(response.isSuccessful) {
                    invited = response.body()!!
                    if (invited.status) {
                        Toast.makeText(context, resources.getString(R.string.msgsuccess), Toast.LENGTH_LONG).show()
                        SharedPreferencesUtility(context).setIsRefCodeEntered()
                    }
                    else
                        Toast.makeText(context ,  resources.getString(R.string.msgfail) , Toast.LENGTH_LONG ).show()
                }
            }
        }
        AppsWebService(context).Invitation(code , imei , userId , listener  )
    }

}
