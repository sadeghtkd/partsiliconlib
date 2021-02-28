package com.partsilicon.partsiliconlib.utils
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.*


fun IsInternetAvailable(context: Context): Boolean {
    var result = false
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        /*cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_LOWPAN) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                    else -> false
                }
            }
        }*/
        result = cm?.activeNetwork!= null && cm?.getNetworkCapabilities(cm.activeNetwork)!=null
    } else {
        cm?.run {
            cm.activeNetworkInfo?.run {
                result = isAvailable && isConnected
            }
        }
    }
    return result
}

fun isInternetAvailable(): Boolean {
    var inetAddress: InetAddress? = null
    try {
        val future: Future<InetAddress> = Executors.newSingleThreadExecutor().submit(object : Callable<InetAddress> {
            override fun call(): InetAddress? {
                return try {
                    InetAddress.getByName("google.com")
                } catch (e: UnknownHostException) {
                    null
                }
            }
        })
        inetAddress = future.get(1000, TimeUnit.MILLISECONDS)
        future.cancel(true)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    } catch (e: ExecutionException) {
        e.printStackTrace()
    } catch (e: TimeoutException) {
        e.printStackTrace()
    }
    return inetAddress != null
}