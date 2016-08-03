package com.sunday.goodhobby.goodhobby.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.os.UserHandle;

import com.sunday.goodhobby.R;
import com.sunday.goodhobby.goodhobby.db.MyDBHelper;
import com.sunday.goodhobby.goodhobby.model.ActivityCollector;
import com.sunday.goodhobby.goodhobby.model.Hobby;

public class HobbyInfoActivity extends BaseActivity {

    String TAG="HobbyInfoActivity";
    Hobby hobby;
    TextView name;
    TextView create_date;
    TextView hobby_description;
    TextView  persistantdays;
    TextView  grade;
    Button sign;
    Button delete;
    MyDBHelper  dbHelper= MyDBHelper.getMyDBHelper(HobbyInfoActivity.this,"goodhobby.db",null,1);
    SQLiteDatabase db= dbHelper.getWritableDatabase();//获取数据库;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hobbyinfo);
        hobby= (Hobby) this.getIntent().getSerializableExtra("hobby_data");
        Log.d(TAG,hobby.getName());
        Log.d(TAG,hobby.getCreateDate());
        Log.d(TAG,hobby.getDescription());
        Log.d(TAG, String.valueOf(hobby.getPersistantDays()));
        Log.d(TAG, String.valueOf(hobby.getGrade()));
        name= (TextView) findViewById(R.id.name1);
        create_date= (TextView) findViewById(R.id.create_date1);
        hobby_description= (TextView) findViewById(R.id.hobby_description1);
        persistantdays= (TextView) findViewById(R.id.persistantdays1);
        grade= (TextView) findViewById(R.id.grade1);
        name.setText(hobby.getName());
        create_date.setText(hobby.getCreateDate());
        hobby_description.setText(hobby.getDescription());
        persistantdays.setText(String.valueOf(hobby.getPersistantDays()));
        grade.setText(String.valueOf(hobby.getGrade()));
        sign= (Button) findViewById(R.id.button_sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("update TBL_HOBBY set PERSISTENTDAYS=? where NAME=?",new String[]{String.valueOf(hobby.getPersistantDays()+1),hobby.getName()});
                //是在hobby的基础上+1，所以在当前activity中只能签到一次，只有退出activity再进入才可以再次签到
                persistantdays.setText(String.valueOf(hobby.getPersistantDays()+1));
                AlertDialog.Builder alert_addSuccess=new AlertDialog.Builder(HobbyInfoActivity.this);
                alert_addSuccess.setTitle("this is monkey");
                alert_addSuccess.setMessage("成功签到");
                alert_addSuccess.show();
            }
        });
        delete= (Button) findViewById(R.id.button_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(HobbyInfoActivity.this);
                alert.setTitle("hello i am monkey");
                alert.setMessage("are you sure to delete.");
                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.execSQL("delete from TBL_HOBBY where NAME=?", new String[]{String.valueOf(hobby.getName())});
                        HobbyInfoActivity.this.finish();
                    }
                });
                alert.setNegativeButton("cancel",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();

            }
        });
    }
}
