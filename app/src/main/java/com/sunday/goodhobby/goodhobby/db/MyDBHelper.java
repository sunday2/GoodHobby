package com.sunday.goodhobby.goodhobby.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2016/7/21.
 */
public class MyDBHelper extends SQLiteOpenHelper{

    public static final String TAG="GoodHobbyTest";
    private static MyDBHelper MyDBHelper=null;
    private static String TableNames[];     //表名对象
    private static String FieldNames[][];   //字段名
    private static String FieldTypes[][];   //字段类型
    private final Context mCtx;    //上下文实例
    private static String message = "";
    private static String NO_CREATE_TABLES = "no tables";
    private MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mCtx=context;
    }

    public static MyDBHelper getMyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
       if(MyDBHelper==null) {
           synchronized (MyDBHelper.class) {
               if (MyDBHelper == null) {
                   MyDBHelper = new MyDBHelper(context, name, factory, version);
                   TableNames = GoodHobbyDBInfo.getTableNames();
                   FieldNames = GoodHobbyDBInfo.getFieldNames();
                   FieldTypes = GoodHobbyDBInfo.getFieldTypes();
               }
           }
       };
        Log.d(TAG, "" + TableNames.length);

        return MyDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db ) {
        if (TableNames == null) {
            message = NO_CREATE_TABLES;
            Log.d(TAG, message);
            return;
        }
        for (int i = 0; i < TableNames.length; i++) {
            String sql2 = "CREATE TABLE " + TableNames[i] + " (";
            for (int j = 0; j < FieldNames[i].length; j++) {
                sql2 += FieldNames[i][j] + " " + FieldTypes[i][j] + ",";
            }
            sql2 = sql2.substring(0, sql2.length() - 1);
            sql2 += ")";
            Log.d(TAG, "自动组装的sql语句."+sql2);
            db.execSQL(sql2);  // 执行语句
        }
        Log.d(TAG,"数据库创建成功.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = 0; i < TableNames[i].length(); i++) {
            String sql = "DROP TABLE IF EXISTS " + TableNames[i];  // 存在则删除
            db.execSQL(sql);
        }
        onCreate(db);   // 执行创建数据库语句
    }
}
