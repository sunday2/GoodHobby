package com.sunday.goodhobby.goodhobby.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sunday.goodhobby.R;
import com.sunday.goodhobby.goodhobby.db.MyDBHelper;
import com.sunday.goodhobby.goodhobby.model.Hobby;
import com.sunday.goodhobby.goodhobby.model.HobbyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowHobbyActivity extends BaseActivity {

    private List<Hobby> hobbyList=new ArrayList<Hobby>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_showhobby);
        initHobbies();
        HobbyAdapter hobbyAdapter=new HobbyAdapter(ShowHobbyActivity.this,R.layout.showhobby_item,hobbyList);
        ListView listView= (ListView) findViewById(R.id.list_view2showbecominghobby);
        listView.setAdapter(hobbyAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hobby hobby=new Hobby();
                hobby.setName(hobbyList.get(i).getName());
                hobby.setCreateDate(hobbyList.get(i).getCreateDate());
                hobby.setDescription(hobbyList.get(i).getDescription());
                hobby.setPersistantDays(hobbyList.get(i).getPersistantDays());
                hobby.setGrade(hobbyList.get(i).getGrade());
                Intent intent=new Intent(ShowHobbyActivity.this,HobbyInfoActivity.class);
               // intent.putExtra("hobby_data",hobby);
                Bundle bundle=new Bundle();
                bundle.putSerializable("hobby_data",hobby);
                intent.putExtras(bundle);
                Log.d("name",hobby.getName());
                Log.d("itemclick","ole");
                startActivity(intent);
                Log.d("anfterstartActivity","ifok");

            }
        });
    }

    private void initHobbies() {
        MyDBHelper dbHelper= MyDBHelper.getMyDBHelper(ShowHobbyActivity.this,"goodhobby.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from TBL_HOBBY where grade!=?",new String []{"3"});
        if(cursor.moveToFirst()){
            do{
                Hobby hobby=new Hobby();
                hobby.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                hobby.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
                hobby.setCreateDate(cursor.getString(cursor.getColumnIndex("CREATEDATE")));
                hobby.setPersistantDays(cursor.getInt(cursor.getColumnIndex("PERSISTENTDAYS")));
                hobby.setGrade(cursor.getInt(cursor.getColumnIndex("GRADE")));
                //hobby.setImageid(R.mipmap.target_icon);
                Log.d("showdb",hobby.getName());
                hobbyList.add(hobby);
            }while(cursor.moveToNext());
        }else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ShowHobbyActivity.this);
            dialog.setTitle("Monkey is here");
            dialog.setMessage("这里什么都没有");
            dialog.show();
        }

    }

}
