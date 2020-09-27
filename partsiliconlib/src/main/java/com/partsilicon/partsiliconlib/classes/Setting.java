package com.partsilicon.partsiliconlib.classes;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.partsilicon.partsiliconlib.pojo.App;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import static android.provider.Settings.Secure.ANDROID_ID;

public class Setting {

    public  String getIMEI (Context context )
    {
/*        if (ActivityCompat.checkSelfPermission(context , Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int api = android.os.Build.VERSION.SDK_INT;*/
            /*
            if (api >= Build.VERSION_CODES.O)
                return  tm.getImei().toString();
            else*/
                return   Settings.Secure.getString(context.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            // requestPermissions  ( new String[] {Manifest.permission.READ_PHONE_STATE} , 1);
/*        } else {
            return  "1" ;
            // else for if they have already given permission
        }*/

    }
}
