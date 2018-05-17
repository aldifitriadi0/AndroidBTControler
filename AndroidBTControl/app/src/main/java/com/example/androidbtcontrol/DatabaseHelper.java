package com.example.androidbtcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ive on 4/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Drone.db";
    public static final String TABLE_NAME = "Drone_Command";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "COMMAND";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (NAME VARCHAR(20) PRIMARY KEY, COMMAND VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String command) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, command);
        long result = db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        if(result == -1)
            return false;
        else
            return true;
    }
  /*  public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }*/

    public String getCommand(String name) {
        name = name.toUpperCase();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select COMMAND from " + TABLE_NAME + " where NAME=\"" + name + "\"", null);

        if(res.getCount() == 0) {
            return "0";
        }

        while (res.moveToNext()) {
            return res.getString(0);
        }
        return "0";
    }

   /* public boolean updateData(String name, String command) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, command);
        db.update(TABLE_NAME, contentValues, "NAME = ?", new String[] { name });
        return true;

    }*/

}
