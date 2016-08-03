package com.sunday.goodhobby.goodhobby.db;

/**
 * Created by Administrator on 2016/7/21.
 */
public class GoodHobbyDBInfo {
    public static String TableNames[]={"TBL_HOBBY"};//表名
    public static String FieldNames[][]={
            {"ID","NAME","CREATEDATE","DESCRIPTION","PERSISTENTDAYS","GRADE"}
    };//字段名
    public static String FieldTypes[][]={
            {"INTEGER PRIMARY KEY AUTOINCREMENT","text","text","text","INTEGER","INTEGER"}
    };//字段类型
    public GoodHobbyDBInfo() {

    }

    public static String[] getTableNames() {
        return TableNames;
    }

    public static String[][] getFieldNames() {
        return FieldNames;
    }

    public static String[][] getFieldTypes() {
        return FieldTypes;
    }
}