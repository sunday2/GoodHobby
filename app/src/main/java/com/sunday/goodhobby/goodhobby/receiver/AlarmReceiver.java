package com.sunday.goodhobby.goodhobby.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sunday.goodhobby.goodhobby.service.LongRunningService;

/**
 * Created by Administrator on 2016/7/28.
 */
public class AlarmReceiver  extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1=new Intent(context, LongRunningService.class);
        context.startService(intent1);
    }
}
