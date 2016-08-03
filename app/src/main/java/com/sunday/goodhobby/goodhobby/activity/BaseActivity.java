package com.sunday.goodhobby.goodhobby.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sunday.goodhobby.goodhobby.model.ActivityCollector;

public class BaseActivity extends AppCompatActivity {

    protected String TAG="BaseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG",getClass().getSimpleName());
        ActivityCollector.activities.add(this);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.activities.remove(this);

    }

}
