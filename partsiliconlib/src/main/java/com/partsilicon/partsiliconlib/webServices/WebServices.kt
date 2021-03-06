package ir.partsilicon.ordermember.webservices



import retrofit2.Call
import retrofit2.http.*
import com.partsilicon.partsiliconlib.pojo.App
import com.partsilicon.partsiliconlib.pojo.AppsObj
import com.partsilicon.partsiliconlib.pojo.InviteRes

interface WebServices {


    @Headers("Content-Type:application/json")
    @POST("List")
    fun getAppList(@Body request : req ): Call<AppsObj>

    @Headers("Content-Type:application/json")
    @POST("Invitation")
    fun Invitation(@Body invite: Invite ): Call<InviteRes>
}