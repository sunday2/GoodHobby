package com.sunday.goodhobby.goodhobby.model;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ActivityCollector {
    public static List<Activity> activities=new ArrayList<Activity>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void removeAll(){
        for(Activity activity:activities){
            if(!activity.isFinishing()){
            activities.remove(activity);
            }
        }
    }
}
