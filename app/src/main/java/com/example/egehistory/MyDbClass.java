package com.example.egehistory;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class MyDbClass extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "MonarchDB.db";
    private static final int DATABASE_VERSION = 1;
    Context context;
    public MyDbClass(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
        this.context = context;
    }

    public ArrayList<DbModelClass> getAllData(){
        try
        {
            ArrayList<DbModelClass> dbModelClassArrayList = new ArrayList<>();
            SQLiteDatabase database =getReadableDatabase();
            if(database!=null){
                Cursor cursor = database.rawQuery("select * from MonarchTABLE",null);
                if(cursor.getCount()!=0){
                    while (cursor.moveToNext()){
                        int _id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String startDate = cursor.getString(2);
                        String finishDate = cursor.getString(3);
                        String description = cursor.getString(4);
                        dbModelClassArrayList.add(new DbModelClass(_id,name,startDate,finishDate,description));
                    }
                    return dbModelClassArrayList;
                }else{

                    Toast.makeText(context,"errorDatabaseTableIsNull", Toast.LENGTH_SHORT).show();
                    return null;
                }

            }else{

                Toast.makeText(context,"errorDatabaseIsNull", Toast.LENGTH_SHORT).show();
                return null;
            }
        }catch (Exception e){
            Toast.makeText(context,"errorDatabase", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
