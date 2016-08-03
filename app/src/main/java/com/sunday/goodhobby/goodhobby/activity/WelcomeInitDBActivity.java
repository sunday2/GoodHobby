package com.sunday.goodhobby.goodhobby.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.sunday.goodhobby.R;
import com.sunday.goodhobby.goodhobby.db.MyDBHelper;
import com.sunday.goodhobby.goodhobby.service.LongRunningService;

public class WelcomeInitDBActivity extends BaseActivity {

    public static final String TAG="GoodHobbyTest";
    public static MyDBHelper MyDBHelper;
    public static SQLiteDatabase db;
    //NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_initdb);
        initialDBdata();
        //创建服务
        Intent intent1=new Intent(WelcomeInitDBActivity.this, LongRunningService.class);
        startService(intent1);
        Button next=(Button)findViewById(R.id.button_click2main);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeInitDBActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        );


    }

    private void initialDBdata() {
        //建立数据库
        MyDBHelper= com.sunday.goodhobby.goodhobby.db.MyDBHelper.getMyDBHelper(this,"goodhobby.db",null,1);
        db=MyDBHelper.getWritableDatabase();
        //Resources res = this.getResources();
        Log.d(TAG, "获取数据库成功.");

    }
}
