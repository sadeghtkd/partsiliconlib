package com.partsilicon.partsiliconlib

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.partsilicon.partsiliconlib.utils.IsInternetAvailable
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

    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
    override fun onResume() {
        super.onResume()
        checkInternet()
    }
    private fun  checkInternet(){
        if(!IsInternetAvailable(this))
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