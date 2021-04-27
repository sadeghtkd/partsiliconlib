package com.example.partsiliconlibproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.partsilicon.partsiliconlib.BaseActivity;
import com.partsilicon.partsiliconlib.dialog.DialogActivity;
import com.partsilicon.partsiliconlib.FeedBackActivity;
import com.partsilicon.partsiliconlib.ItemFragment;
import com.partsilicon.partsiliconlib.utils.SharedPreferencesUtility;
import com.partsilicon.partsiliconlib.utils.UtilsKt;

import static com.partsilicon.partsiliconlib.utils.UtilsUIKt.setUnreadActionMenu;

public class MainActivity extends BaseActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle( String.valueOf( UtilsKt.GetVersionCode(this)));
        // تست کد معرف

        //if (!new SharedPreferencesUtility(this).isRefCodeEntered())
            //startActivity(new Intent(this , ReferalCodeActivity.class ));
        /*Intent intnt = new Intent(this , WebViewActivity.class);
        intnt.putExtra("url","https://google.com");
        intnt.putExtra("title","google.com");
        startActivity(intnt);*/
/*
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, ItemFragment.newInstance("09157690095")).commit();

        Intent refCodeIntent = new Intent(this, ReferalCodeActivity.class);
        refCodeIntent.putExtra("userId" , "09157690095");
        startActivity(refCodeIntent);
        */
        // تست کد معرف
        startActivity(new Intent(this , DialogActivity.class));
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

    @Override
    public void onListFragmentInteraction(String item) {

    }
}
