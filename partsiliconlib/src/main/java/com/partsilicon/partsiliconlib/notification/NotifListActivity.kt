package com.partsilicon.partsiliconlib.notification

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.partsilicon.partsiliconlib.BaseActivity
import com.partsilicon.partsiliconlib.R
import com.partsilicon.partsiliconlib.notification.model.Notif
import com.partsilicon.partsiliconlib.utils.getItemActionIntent
import kotlinx.android.synthetic.main.activity_notif_list.*
import java.lang.Exception

class NotifListActivity : BaseActivity(), NotifFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notif_list)
        setSupportActionBar(toolbar)
    }

    override fun onListFragmentInteraction(item: Notif?) {
        val intent = getItemActionIntent(this , item)
        if(intent != null)
            try {
                startActivity(intent)
            }catch (e:Exception){
                e.printStackTrace()
            }
    }

}
