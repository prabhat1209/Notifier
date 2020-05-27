package com.example.notifier.clicked;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class CheckNotification extends SQLiteOpenHelper {
    public CheckNotification(@Nullable Context context) {
        super(context, Read.DB_NAME,null,Read.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("start");
        try {

            String create = "CREATE TABLE " + Read.TABLE_NAME + " ("
                    + Read.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Read.KEY_CLICKED
                    + " TEXT)";
            Log.d("cdbPrabhat", "Query being run is : " + create);

            db.execSQL(create);
        }catch (Exception ex){System.out.println("vdcvhvdshcvsdvchdvcdvcwdvc!!!!"+ex);}
            System.out.println("ok");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public void add(DoneNotification notify){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Read.KEY_CLICKED, notify.getTitle());

        db.insert(Read.TABLE_NAME, null, values);
        Log.d("dbPrabhat", "Successfully inserted");
        db.close();
    }

    public int getCount(){
        String query = "SELECT * FROM "+ Read.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        return c.getCount();
    }

    public boolean isExist(String click){
        boolean x = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Read.TABLE_NAME;
        System.out.println("cursor");
        Cursor cursor = null;
        try{
             cursor = db.rawQuery(select, null);    
        }catch (Exception e){
            Log.i("Ye  ", String.valueOf(e));
        }
        
        System.out.println("chal cursor");
        while(cursor.moveToNext()){
            System.out.println(cursor.getString(cursor.getColumnIndex("checked")));
            if(cursor.getString(cursor.getColumnIndex("checked")).equals(click)){
                x = true;
                break;
            }
        }
        db.close();
        return x;
    }

}
