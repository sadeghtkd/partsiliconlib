package ir.partsilicon.ordermember.webservices



import retrofit2.Call
import retrofit2.http.*
import com.partsilicon.partsiliconlib.pojo.App
import com.partsilicon.partsiliconlib.pojo.AppsObj

interface WebServices {


    @Headers("Content-Type:application/json")
    @POST("List")
    fun getAppList( ): Call<AppsObj>


}