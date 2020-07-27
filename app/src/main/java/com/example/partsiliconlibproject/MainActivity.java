package com.example.partsiliconlibproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.partsilicon.partsiliconlib.FeedBackActivity;
import com.partsilicon.partsiliconlib.WebViewActivity;
import com.partsilicon.partsiliconlib.notification.NotifListActivity;
import com.partsilicon.partsiliconlib.utils.SharedPreferencesUtility;
import com.partsilicon.partsiliconlib.utils.UtilsKt;
import com.partsilicon.partsiliconlib.utils.UtilsUIKt;

import static com.partsilicon.partsiliconlib.utils.UtilsUIKt.setUnreadActionMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle( String.valueOf( UtilsKt.GetVersionCode(this)));
        //startActivity(new Intent(this , NotifListActivity.class));
        /*Intent intnt = new Intent(this , WebViewActivity.class);
        intnt.putExtra("url","https://google.com");
        intnt.putExtra("title","google.com");
        startActivity(intnt);*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final SharedPreferencesUtility sharedPreferencesUtility = new SharedPreferencesUtility(this);
        if (sharedPreferencesUtility.isFirstLaunch()) {
            startActivity(new Intent(this , FeedBackActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        final View action_notifications = menu.findItem(R.id.action_notifications).getActionView();
        setUnreadActionMenu(this , action_notifications);
        return true;
    }
}
