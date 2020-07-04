package com.example.egehistory;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class MyDbClass extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "MonarchDB1.db";
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
                        try {
                            String image = cursor.getString(5);
                            dbModelClassArrayList.add(new DbModelClass(_id,name,startDate,finishDate,description,image));


                        }catch (Exception e){
                            Log.d("READ_DATABASE","T2"+String.valueOf(e));
                        }

                        Log.d("READ_DATABASE",name);
                    }

                    return dbModelClassArrayList;
                }else{
                    Log.d("NameMonarch","errorDatabaseTableIsNull");
                    Toast.makeText(context,"errorDatabaseTableIsNull", Toast.LENGTH_SHORT).show();
                    return null;
                }

            }else{
                Log.d("NameMonarch","error_2");
                Toast.makeText(context,"errorDatabaseIsNull", Toast.LENGTH_SHORT).show();
                return null;
            }
        }catch (Exception e){
            Log.d("NameMonarch",String.valueOf(e));
            Toast.makeText(context,"errorDatabase", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
