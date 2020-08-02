package com.partsilicon.partsiliconlib

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_feed_back.*
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.partsilicon.partsiliconlib.classes.getMarketName
import com.partsilicon.partsiliconlib.utils.SharedPreferencesUtility

class FeedBackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_back)
        val ai = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        btnOk.setOnClickListener{

            val market = getMarketName(this) //ai.metaData.get("market")?.toString()
            try {
                if (market == "bazaar") {
                    val intent = Intent(Intent.ACTION_EDIT)
                    intent.data = Uri.parse("bazaar://details?id=" + getPackageName())
                    intent.setPackage("com.farsitel.bazaar")
                    startActivity(intent)
                } else if (market == "myket") {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("myket://comment?id=" + getPackageName())
                    startActivity(intent)
                } else if (market == "charkhoneh") {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("jhoobin://comment?q=" + getPackageName())
                    startActivity(intent)
                } else
                    Toast.makeText(this, "market not defined", Toast.LENGTH_LONG).show()
            }catch (e:Exception){
                Toast.makeText(this, "مارکت بر روی دستگاه نصب نیست", Toast.LENGTH_LONG).show()
            }
            SharedPreferencesUtility(this).setIsFirstLaunch()
            finish()
        }
        btnNo.setOnClickListener {
            //email
            try {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:") // only email apps should handle this
                val supportEmail = ai.metaData.get("support_email")
                if (supportEmail.toString().isNullOrEmpty())
                    Toast.makeText(this, "support_email not defined", Toast.LENGTH_LONG).show()
                val addresses = arrayOf(supportEmail?.toString())
                intent.putExtra(Intent.EXTRA_EMAIL, addresses)
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedbackNo) + packageName)
                if (intent.resolveActivity(packageManager) != null) {
                    Toast.makeText(this, getString(R.string.feedbackTellUs), Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }
            }catch (e:Exception){

            }
            SharedPreferencesUtility(this).setIsFirstLaunch()
            finish()
        }


        btnNext.setOnClickListener(){

        }
    }

    override fun onBackPressed() {
        finish()
    }
}
