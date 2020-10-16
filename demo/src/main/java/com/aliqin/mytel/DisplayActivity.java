//package com.aliqin.mytel;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import com.aliqin.mytel.login.OneKeyLoginActivity;
//import com.aliqin.mytel.login.OneKeyLoginDelayActivity;
//
//import java.util.HashMap;
//
//import static com.aliqin.mytel.Constant.LOGIN;
//import static com.aliqin.mytel.Constant.LOGIN_TYPE;
//import static com.aliqin.mytel.Constant.THEME_KEY;
//import static com.aliqin.mytel.Constant.TYPES;
//
//
//public class DisplayActivity extends Activity {
//
//    private HashMap<String, Constant.UI_TYPE> mThemeMap;
//    private ListView mListView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display);
//        initData();
//        final int type = getIntent().getIntExtra(LOGIN_TYPE, LOGIN);
//        mListView = findViewById(R.id.lv_type);
//        ArrayAdapter<String> pArrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.tv_type, TYPES);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (type == LOGIN) {
//
//                } else {
//                    Intent pIntent = new Intent(DisplayActivity.this, OneKeyLoginDelayActivity.class);
//                    pIntent.putExtra(THEME_KEY, Constant.UI_TYPE.values()[position]);
//                    startActivity(pIntent);
//
//                }
//            }
//        });
//        Intent pIntent = new Intent(DisplayActivity.this, OneKeyLoginActivity.class);
//        pIntent.putExtra(THEME_KEY, Constant.UI_TYPE.values()[6]);
//        startActivity(pIntent);
//        mListView.setAdapter(pArrayAdapter);
//    }
//
//    private void initData() {
//        mThemeMap = new HashMap<>();
//        for (int i = 0; i < TYPES.length; i++) {
//            mThemeMap.put(TYPES[i], Constant.UI_TYPE.values()[i]);
//        }
//    }
//}
