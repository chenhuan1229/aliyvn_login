package com.aliqin.mytel.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.aliqin.mytel.Constant;
import com.aliqin.mytel.MessageActivity;
import com.aliqin.mytel.R;
import com.aliqin.mytel.config.AuthPageConfig;
import com.aliqin.mytel.config.BaseUIConfig;
import com.aliqin.mytel.uitls.ExecutorManager;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.TokenResultListener;
import com.mobile.auth.gatewayauth.model.TokenRet;

import static com.aliqin.mytel.Constant.THEME_KEY;
import static com.aliqin.mytel.uitls.MockRequest.getPhoneNumber;

/**
 * 进app直接登录的场景
 */
public class OneKeyLoginActivity extends Activity {
    private static final String TAG = OneKeyLoginActivity.class.getSimpleName();

    private TextView mTvResult;
    private PhoneNumberAuthHelper mPhoneNumberAuthHelper;
    private TokenResultListener mTokenResultListener;
    private ProgressDialog mProgressDialog;
    private Constant.UI_TYPE mUIType;
    private AuthPageConfig mUIConfig;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUIType =  Constant.UI_TYPE.values()[6];
        setContentView(R.layout.activity_login);
        mTvResult = findViewById(R.id.tv_result);
        sdkInit();
        mUIConfig = BaseUIConfig.init(mUIType, this, mPhoneNumberAuthHelper);
        oneKeyLogin();
    }


    public void sdkInit() {
        mTokenResultListener = new TokenResultListener() {
            @Override
            public void onTokenSuccess(String s) {
                hideLoadingDialog();
                TokenRet tokenRet = null;
                try {
                    tokenRet = JSON.parseObject(s, TokenRet.class);
                    if (ResultCode.CODE_START_AUTHPAGE_SUCCESS.equals(tokenRet.getCode())) {
                        Log.i("TAG", "唤起授权页成功：" + s);
                    }

                    if (ResultCode.CODE_GET_TOKEN_SUCCESS.equals(tokenRet.getCode())) {
                        Log.i("TAG", "获取token成功：" + s);
                        getResultWithToken(tokenRet.getToken());
                        mUIConfig.release();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTokenFailed(String s) {
                Log.e(TAG, "获取token失败：" + s);
                hideLoadingDialog();
                TokenRet tokenRet = null;
                mPhoneNumberAuthHelper.quitLoginPage();
                try {
                    tokenRet = JSON.parseObject(s, TokenRet.class);
                    if (ResultCode.CODE_ERROR_USER_CANCEL.equals(tokenRet.getCode())) {
                        //模拟的是必须登录 否则直接退出app的场景
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "一键登录失败切换到其他登录方式", Toast.LENGTH_SHORT).show();
                        Intent pIntent = new Intent(OneKeyLoginActivity.this, MessageActivity.class);
                        startActivityForResult(pIntent, 1002);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mUIConfig.release();
            }
        };
        mPhoneNumberAuthHelper = PhoneNumberAuthHelper.getInstance(this, mTokenResultListener);
    }

    /**
     * 进入app就需要登录的场景使用
     */
    private void oneKeyLogin() {
        mPhoneNumberAuthHelper = PhoneNumberAuthHelper.getInstance(getApplicationContext(), mTokenResultListener);
        mUIConfig.configAuthPage();
        getLoginToken(5000);
    }

    /**
     * 拉起授权页
     * @param timeout 超时时间
     */
    public void getLoginToken(int timeout) {
        mPhoneNumberAuthHelper.getLoginToken(this, timeout);
        showLoadingDialog("正在唤起授权页");
    }


    public void getResultWithToken(final String token) {
        ExecutorManager.run(new Runnable() {
            @Override
            public void run() {
                final String result = getPhoneNumber(token);
                OneKeyLoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvResult.setText("登陆成功：" + result);
                        mPhoneNumberAuthHelper.quitLoginPage();
                    }
                });
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1002) {
            if (resultCode == 1) {
                mTvResult.setText("登陆成功：" + data.getStringExtra("result"));
            } else {
                //模拟的是必须登录 否则直接退出app的场景
                finish();
            }
        }
    }



    public void showLoadingDialog(String hint) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        mProgressDialog.setMessage(hint);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public void hideLoadingDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUIConfig.onResume();
    }
}
