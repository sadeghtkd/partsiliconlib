package ir.partsilicon.ordermember.webservices

import android.content.Context
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback
import com.partsilicon.partsiliconlib.pojo.App
import com.partsilicon.partsiliconlib.pojo.AppsObj


class AppsWebService (val context: Context){
    private var webServices : WebServices = RetrofitClientInstance.retrofitInstance?.create(WebServices::class.java)!!


    fun  getAppList(callback: MyCallback<AppsObj>){
        val call = webServices.getAppList() //  .getCoinPriceList(token!!, Encoder.encode(BuildConfig.MARKET_NO.toString()), BuildConfig.MARKET_NO);
        call?.enqueue(callback)

    }


}