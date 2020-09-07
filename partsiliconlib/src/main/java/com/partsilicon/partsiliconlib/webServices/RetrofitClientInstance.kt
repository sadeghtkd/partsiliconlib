package ir.partsilicon.ordermember.webservices

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://partsilicon.com/PartSiliconLib/RefApp/"

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                        .readTimeout(40, TimeUnit.SECONDS)
                        .connectTimeout(42, TimeUnit.SECONDS)
                        .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                    .build()
            }
            return retrofit
        }
}
