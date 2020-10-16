package com.aliqin.mytel;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.PermissionChecker;
import android.view.View;

import com.aliqin.mytel.auth.PersonActivity;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.nirvana.tools.operation.CellularNetworkAnalysisUtils;

import static com.aliqin.mytel.Constant.LOGIN;
import static com.aliqin.mytel.Constant.LOGIN_DELAY;
import static com.aliqin.mytel.Constant.LOGIN_TYPE;


public class ModeSelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        if (Build.VERSION.SDK_INT >= 23) {
            if (PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10001);
            }
        }

        findViewById(R.id.auth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeSelectActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });

//        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ModeSelectActivity.this, DisplayActivity.class);
//                intent.putExtra(LOGIN_TYPE, LOGIN);
//                startActivity(intent);
//            }
//        });
//
//        findViewById(R.id.login_delay).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ModeSelectActivity.this, DisplayActivity.class);
//                intent.putExtra(LOGIN_TYPE, LOGIN_DELAY);
//                startActivity(intent);
//            }
//        });

        findViewById(R.id.env_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CellularNetworkAnalysisUtils.checkCellularNetworkStatus(
                        PhoneNumberAuthHelper.getInstance(ModeSelectActivity.this, null),
                        ModeSelectActivity.this,
                        "https://",
                        "www.baidu.com"
                        );
            }
        });
    }
}

