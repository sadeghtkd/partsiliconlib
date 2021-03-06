package ir.partsilicon.ordermember.webservices

import android.content.Context
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback
import com.partsilicon.partsiliconlib.pojo.App
import com.partsilicon.partsiliconlib.pojo.AppsObj
import com.partsilicon.partsiliconlib.pojo.InviteRes


class AppsWebService (val context: Context){
    private var webServices : WebServices = RetrofitClientInstance.retrofitInstance?.create(WebServices::class.java)!!


    fun  getAppList(hostApp:String , imei:String , userHostId :String,  callback: MyCallback<AppsObj>){
        var request:req =    req( hostApp , imei,userHostId)
        val call = webServices.getAppList(request) //  .getCoinPriceList(token!!, Encoder.encode(BuildConfig.MARKET_NO.toString()), BuildConfig.MARKET_NO);
        call?.enqueue(callback)

    }
    fun  Invitation(code:String , imei:String ,userId:String,  callback: MyCallback<InviteRes>){
        var invite:Invite =    Invite( code , imei , userId)
        val call = webServices.Invitation(invite)
        call?.enqueue(callback)

    }
}

class  Invite constructor(code : String , ime:String , userId :String) {
    var  code : String  = code
    var imei:String =ime
    var userId:String = userId
}
class  req constructor(host : String , ime:String , userHostId :String) {
    var  hostApp : String  = host
    var imei:String =ime
    var userHostId:String = userHostId
}