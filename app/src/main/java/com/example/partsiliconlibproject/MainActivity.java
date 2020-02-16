package com.example.partsiliconlibproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.partsilicon.partsiliconlib.FeedBackActivity;
import com.partsilicon.partsiliconlib.utils.SharedPreferencesUtility;
import com.partsilicon.partsiliconlib.utils.UtilsKt;
import com.partsilicon.partsiliconlib.utils.UtilsUIKt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle( UtilsKt.ToPersianNumbers("12345"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final SharedPreferencesUtility sharedPreferencesUtility = new SharedPreferencesUtility(this);
        if (sharedPreferencesUtility.isFirstLaunch()) {
            startActivity(new Intent(this , FeedBackActivity.class));
        }
    }
}
