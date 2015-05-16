package com.mycompany.singhinderdeep.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by singh.inderdeep on 09-05-2015.
 */
public class DatabaseOpr  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="USR_INF_DB";

    public static final String KEY_USER_NAME="USER_NAME";
    public static final String KEY_USER_NAME_TYPE="TEXT";
    public static final String KEY_USER_PASS="USER_PASS";
    public static final String KEY_USER_PASS_TYPE="TEXT";
    public static final String TABLE_REG_INFO="USER_INFO";

    public DatabaseOpr(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DBOpr","Successfully created DB");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUERY="CREATE TABLE "+ TABLE_REG_INFO+"("+KEY_USER_NAME+ " " + KEY_USER_NAME_TYPE + "," + KEY_USER_PASS+" "+KEY_USER_PASS_TYPE+");";
        db.execSQL(CREATE_QUERY);
        Log.d("DBOpr", "Successfully created Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long putUserInfo(UserInfo usrinf)  {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_USER_NAME,usrinf.getUserName());
        values.put(KEY_USER_PASS, usrinf.getUserPass());

        // Insert row
        long status=db.insert(TABLE_REG_INFO,null,values);
        Log.d("DBOpr", "Insert Successfull");

        db.close();
        Log.d("DBOpr", "Connection closed");

        return status;
    }

    public long updateUserInfo(UserInfo usrinf) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_PASS, usrinf.getUserPass());

        // update row
        long status=db.update(TABLE_REG_INFO, values, KEY_USER_NAME+ " = ?", new String[] { usrinf.getUserName()});
        Log.d("DBOpr","Update Successful");

        return status;
    }

    public long deleteUserInfo(UserInfo usrinf) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, usrinf.getUserName());

        // delete row
        long status=db.delete(TABLE_REG_INFO, KEY_USER_NAME + " = ?", new String[]{usrinf.getUserName()});
        Log.d("DBOpr","Delete Successful");

        return status;
    }

    public Cursor getUserInfo()  {
        SQLiteDatabase db=this.getReadableDatabase();

        String [] columns={KEY_USER_NAME, KEY_USER_PASS};

        Cursor cr=db.query(TABLE_REG_INFO,columns,null,null,null,null,null);
        Log.d("DBOpr","Select Successful");

        return cr;
    }
}

