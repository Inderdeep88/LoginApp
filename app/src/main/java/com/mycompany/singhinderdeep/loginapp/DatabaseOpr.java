package com.mycompany.singhinderdeep.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by singh.inderdeep on 09-05-2015.
 */
public class DatabaseOpr  extends SQLiteOpenHelper {
    public static final int dataBaseVer=1;
    public String CREATE_QUERY="CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+"("+TableData.TableInfo.USER_NAME+" TEXT," + TableData.TableInfo.USER_PASS+" TEXT);";

    public DatabaseOpr(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, dataBaseVer);
        Log.d("DBOpr","Successfully created DB");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("DBOpr", "Successfully created Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putUserInfo(DatabaseOpr dbo,String name,String pass)  {
        SQLiteDatabase sq=dbo.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME,name);
        cv.put(TableData.TableInfo.USER_PASS, pass);
        long val=sq.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("DBOpr","1 row inserted");
    }
}
