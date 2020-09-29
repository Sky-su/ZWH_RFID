package com.company.zwh_rfid.database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLFunction {

    static SQLBaseHelper helper;


    public static void initTable(Context context){
        helper = new SQLBaseHelper(context);
        helper.getReadableDatabase();
    }

    /**【插入数据】**/
    public static void insert(Context context,String tableName,String sqlType , Object[] data){

        Log.i("TAG:","插入数据到数据库表：person中:"+data.toString());

        DBManager sqlManager = new DBManager(context);
        helper = new SQLBaseHelper(context);
        helper.getWritableDatabase();
        String sql = "insert into "+tableName+" ("+ sqlType+")"+" values ( ? , ?)";
        Object[] bindArgs = data;
        sqlManager.updateSQLite( sql , bindArgs );
    }
    public static ArrayList<HashMap<String, String>> queryID(Context context, String where1 ){
        DBManager sqlManager = new DBManager(context);
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String sql = "select * from person ";
        list = sqlManager.querySQLite(sql,new String[] {"%","%"});
        Log.i("TAG:","查询完毕，返回数据：" + list.size());


        return list;
    }

    public void select(Context context, String tableName) {
        DBManager sqlManager = new DBManager(context);
        String sql = "select * from "+tableName ;
        Cursor cursor = (Cursor) sqlManager.querySQLiteionList(sql,new String[]{"%"});

        if (cursor.moveToFirst()){

        }

    }

    /**【模糊查询】**/
    public static ArrayList<HashMap<String, String>> query(Context context, String where1 , String where2){
        DBManager sqlManager = new DBManager(context);
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String sql = "select * from person where name like ? and info like ?";
        if(where1 == null){
            list = sqlManager.querySQLite(sql,new String[] {"%","%"});
        }else{
            where1 = "%" + where1 + "%";
            where2 = "%" + where2 + "%";
            list = sqlManager.querySQLite(sql,new String[] { where1 , where2 } );
        }

        Log.i("TAG:","查询完毕，返回数据：" + list.size());

        return list;
    }

    /**【删除数据】**/
    public static void delete(Context context , String tableName, Object[] data){
        DBManager sqlmanager = new DBManager(context);
        String sql = "delete from +"+tableName+" where  ? ";
        sqlmanager.updateSQLite(sql , data);
    }

    /**【更新数据】**/
    public static void update(Context context ,String tableName, Object[]data){
        helper = new SQLBaseHelper(context);
        helper.getReadableDatabase();
        DBManager sqlManager = new DBManager(context);
        String sql = "update "+tableName+" set ? where ?";
        sqlManager.updateSQLite(sql,data);
    }

}