package com.partsilicon.partsiliconlib

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.partsilicon.partsiliconlib.utils.IsInternetAvailable
import com.partsilicon.partsiliconlib.utils.isInternetAvailable
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.lang.Exception

open class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewPump.init(
                ViewPump.builder()
                        .addInterceptor(
                                CalligraphyInterceptor(
                                        CalligraphyConfig.Builder()
                                                .setDefaultFontPath("fonts/IranYekan.ttf")
                                                .build()
                                )
                        )
                        .build()
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(ContextCompat.getColor(this , R.color.colorPrimaryDark))
        }
    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
    override fun onResume() {
        super.onResume()
        //checkInternet()
        //بخاطر باگههایی که داشت چک کردن اینترنت غیرفعال شد
    }
    private fun  checkInternet(){
        if(!isInternetAvailable())
        {
            try {
                var dlg = AlertDialog.Builder(this)
                with(dlg){
                    setIcon(AppCompatResources.getDrawable(this@BaseActivity, R.drawable.ic_wifi_off_24dp))
                    setMessage(R.string.noConnection)
                    setPositiveButton(R.string.btnRetry) { dialogInterface, i ->
                        dialogInterface.dismiss()
                        if(IsInternetAvailable(this@BaseActivity)) {
                            recreate()
                        }else
                            checkInternet()

                    }
                    setNegativeButton(R.string.btnCancel){ dialogInterface, i ->
                        dialogInterface.dismiss()
                        //finish()
                    }
                    show()
                }
            }catch (e:Exception){

            }

        }
    }
}