package com.aliqin.mytel;

import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.TokenResultListener;



public class DemoApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        PhoneNumberAuthHelper authHelper = PhoneNumberAuthHelper.getInstance(this, new TokenResultListener() {
            @Override
            public void onTokenSuccess(String ret) {
                Log.e("init", "onTokenSuccess: " + ret);
            }

            @Override
            public void onTokenFailed(String ret) {
                Log.e("init", "onTokenFailed: " + ret);
            }
        });
        authHelper.setAuthSDKInfo("7KHffk2Cn1j17+QVA2zbJfdDteDSUDspB/s+FUoAhyXmQ/wueAQBcpMDOVLrp5lt5BDIGxDrCuTBZk7TcR4CxAQvHnJUPIaCI5dscbBFqHgHVI8Yoy0nYwsFo8Gyd2RZ6MbUAZr3lsnPQsA+UW1MZY9EP94x0TrXmwEJkU5xJgmOJfCSekYWHP5xNc0as/aWkTmNrjFyb5//93cAMwQllH0FFEFF+GEd7XMvm6ap/g4BD8676+z29MbePXPjoY6u3VrNTMkksQHW1EolxJkw+y1FhIxpm9II");
        authHelper.getReporter().setLoggerEnable(BuildConfig.DEBUG);
    }

}
