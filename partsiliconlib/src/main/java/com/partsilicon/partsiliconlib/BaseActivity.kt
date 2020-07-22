package com.partsilicon.partsiliconlib

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.partsilicon.partsiliconlib.R
import com.partsilicon.partsiliconlib.utils.IsInternetAvailable
import java.lang.Exception

open class BaseActivity : AppCompatActivity(){

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