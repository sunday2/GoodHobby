package com.sunday.goodhobby.goodhobby.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.sunday.goodhobby.R;
import com.sunday.goodhobby.goodhobby.activity.WelcomeInitDBActivity;
import com.sunday.goodhobby.goodhobby.receiver.AlarmReceiver;

import java.util.Date;

public class LongRunningService extends Service {
    public LongRunningService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override    //此处写服务要处理的逻辑业务
            public void run() {
                Log.d("LongRunningService","excuted at"+new Date().toString());
                //创建通知
                Notification.Builder builder=new Notification.Builder(getApplicationContext());
                builder.setTicker("你有新消息了");
                builder.setContentTitle("monkey提醒你");
                builder.setContentText("今天的习惯你完成了吗？");
                builder.setSmallIcon(R.mipmap.bear);
                //点击进入app欢迎页面
                Intent intent1=new Intent(getApplicationContext(), WelcomeInitDBActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent1,PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setContentIntent(pendingIntent);
                Notification notification=builder.build();
                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1,notification);


            }
        }).start();
        //定时提醒
        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour=60*60*1000;//这是一个小时的的毫秒数
        //long triggerAtTime= System.currentTimeMillis()+24*anHour-(System.currentTimeMillis()/(24*anHour))+7*anHour;//在隔天7点
        //这是测试用的，所以设置为每隔5s提醒一次，实际可以设置为每天7点钟
        long triggerAtTime= System.currentTimeMillis()+5000;//5s后
        Intent intent2=new Intent(this,AlarmReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,intent2,0);
        manager.set(AlarmManager.RTC_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
