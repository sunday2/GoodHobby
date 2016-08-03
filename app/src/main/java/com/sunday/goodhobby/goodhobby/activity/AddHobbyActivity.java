package com.sunday.goodhobby.goodhobby.activity;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.sunday.goodhobby.R;
import com.sunday.goodhobby.goodhobby.db.MyDBHelper;

import java.util.Calendar;

public class AddHobbyActivity extends BaseActivity {
    private String TAG="goodhobbytest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addhobby);
        EditText createDate= (EditText) findViewById(R.id.create_date);
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        String date=year+"年"+month+"月"+day+"日";
        Log.d(TAG,date);
        createDate.setText(date);

        Button add= (Button) findViewById(R.id.add_hobby);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText hobby_name= (EditText) findViewById(R.id.hobby_name);
                EditText hobby_description= (EditText) findViewById(R.id.hobby_description);
                EditText hobby_createdate= (EditText) findViewById(R.id.create_date);
                String name=hobby_name.getText().toString();  //获取EditText中的内容
                String description=hobby_description.getText().toString();
                String date=hobby_createdate.getText().toString();
                ContentValues values=new ContentValues();
                values.put("NAME",name);
                values.put("DESCRIPTION",description);
                values.put("CREATEDATE",date);
                values.put("PERSISTENTDAYS",0);  //hobby创建后的已经坚持天数为0
                values.put("GRADE",1);//hobby创建后的等级为1级
                MyDBHelper dbHelper= MyDBHelper.getMyDBHelper(AddHobbyActivity.this,"goodhobby.db",null,1);
                SQLiteDatabase db= dbHelper.getWritableDatabase();//获取数据库
                db.insert("TBL_HOBBY",null,values);//向TBL_HOBBY表添加一条记录

                AlertDialog.Builder dialog=new AlertDialog.Builder(AddHobbyActivity.this);  //添加成功后返回一个对话框
                dialog.setTitle("你好，我是monkey！");
                dialog.setMessage("你已经成功添加一个习惯了，加油，看好你哦！");
                dialog.setCancelable(false);
                dialog.setPositiveButton("GoOn",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.setNegativeButton("Goback", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();


            }
        });

    }
}
