package com.aigerimbb.android.tasbeeh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aigerimbb.android.tasbeeh.model.Tasbeeh;

import java.util.ArrayList;

/**
 * Created by Beishenbekova on 05.11.2016.
 */

public class DB_Connection extends SQLiteOpenHelper {
    public static final String DBNAME="MyTasbeeh.db";
    public static final String DBLOCATION="/data/data/com.aigerimbb.android.tasbeeh/databases/";
    private Context context;
    private SQLiteDatabase mDatabase=null;


    public DB_Connection(Context context) {
        super(context, DBNAME, null , 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase(){
        String dbPath=context.getDatabasePath(DBNAME).getPath();
        if(mDatabase!=null && mDatabase.isOpen()){
            return;
        }
        mDatabase=SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if(mDatabase!=null){
            mDatabase.close();
        }
    }

    public ArrayList<Tasbeeh> getList(){
        ArrayList<Tasbeeh> list=new ArrayList<Tasbeeh>();
        Tasbeeh tasbeeh;

        openDatabase();
        Cursor cursor=mDatabase.rawQuery("select id,name,name_tr,content,max_count from tasbeeh",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            tasbeeh=new Tasbeeh(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
            list.add(tasbeeh);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return list;
    }

    public ArrayList getTasbeehNames(){
        ArrayList<String> array=new ArrayList<String>();

        openDatabase();;
        Cursor cursor=mDatabase.rawQuery("select name from tasbeeh",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            array.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return array;
    }

    public Tasbeeh getTasbeeh(String name){
        Tasbeeh tasbeeh=null;
        String sql="select id,name,name_tr,content,max_count from tasbeeh where name like \'%"+name+"%\'";
        openDatabase();
        Cursor cursor=mDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        tasbeeh=new Tasbeeh(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));

        cursor.close();
        closeDatabase();
        return tasbeeh;
    }

    public void updateTasbeeh(String oldname,String name,String name_tr,String content,int max_count){
      /*  openDatabase();
        String sql="UPDATE tasbeeh SET name=\'"+name+"\' ,"+
                    " name_tr=\'"+name_tr+"\',"+
                    " content=\'"+content+"\',"+
                    " max_count = "+max_count+
                    "WHERE name like \'"+oldname+"\'";

        mDatabase.execSQL(sql);*/
        openDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("name_tr",name_tr);
        contentValues.put("content",content);
        contentValues.put("max_count",max_count);
        mDatabase.update("tasbeeh",contentValues," name like \'"+oldname+"\'",null);

        closeDatabase();
    }

    public boolean insertTasbeeh(String name,String name_tr,String content,int max_count){
        openDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("name_tr",name_tr);
        contentValues.put("content",content);
        contentValues.put("max_count",max_count);
        mDatabase.insert("tasbeeh",null,contentValues);
        closeDatabase();
        return true;
    }
    public void deleteTasbeeh(String name){
        openDatabase();
        String sql="DELETE FROM tasbeeh WHERE name like \'"+name+"\'";
        mDatabase.execSQL(sql);
        closeDatabase();
    }
}
