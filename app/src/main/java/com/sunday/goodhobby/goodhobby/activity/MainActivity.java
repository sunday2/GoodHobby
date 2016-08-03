package com.sunday.goodhobby.goodhobby.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sunday.goodhobby.R;
import com.sunday.goodhobby.goodhobby.model.Item;
import com.sunday.goodhobby.goodhobby.model.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<Item> itemList=new ArrayList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //取消通知
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
        initItems();
        ItemAdapter adapter=new ItemAdapter(MainActivity.this,R.layout.listview_item,itemList);
        ListView listView= (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item=itemList.get(i);
                Toast.makeText(MainActivity.this,item.getName(),Toast.LENGTH_SHORT).show();
                Intent intent;
                switch (item.getName()){
                    case "add hobby":intent=new Intent(MainActivity.this,AddHobbyActivity.class);
                                     startActivity(intent);break;
                    case "becoming hobby":intent=new Intent(MainActivity.this,ShowHobbyActivity.class);
                        startActivity(intent);break;
                    case"be hobby":intent=new Intent(MainActivity.this,ShowHobbyActivity2.class);
                                    startActivity(intent);break;
                    case"delete hobby":intent=new Intent(MainActivity.this,ShowHobbyActivity3.class);
                                      startActivity(intent);break;
                    default:break;
            }
        }
        });}


    private void initItems(){
        Item item1=new Item("add hobby");
        Item item2=new Item("becoming hobby");
        Item item3=new Item("be hobby");
        Item item4=new Item("delete hobby");
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);

    }
}
