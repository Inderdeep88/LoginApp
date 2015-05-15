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

    public void putUserInfo(UserInfo usrinf)  {
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME,usrinf.userName);
        cv.put(TableData.TableInfo.USER_PASS,usrinf.userPass);
        long val=sq.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("DBOpr","1 row inserted");
        sq.close();
        Log.d("DBOpr", "Connection closed");
    }

    /*
    public Cursor getUserpass(UserInfo usrinf)  {
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME,usrinf.userName);
        cv.put(TableData.TableInfo.USER_PASS,usrinf.userPass);
        long val=sq.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("DBOpr","1 row inserted");
        sq.close();
        Log.d("DBOpr", "Connection closed");
    }
*/
    public int updateUserInfo(UserInfo usrinf) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableData.TableInfo.USER_PASS, usrinf.userPass);

        // updating row
        return db.update(TableData.TableInfo.TABLE_NAME, values, TableData.TableInfo.USER_NAME+ " = ?",
                new String[] { usrinf.userName});
    }

    public int deleteUserInfo(UserInfo usrinf) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableData.TableInfo.USER_NAME, usrinf.userName);

        // updating row
        return db.delete(TableData.TableInfo.TABLE_NAME, TableData.TableInfo.USER_NAME+ " = ?",
                new String[] { usrinf.userName});
    }

    public Cursor getUserInfo()  {
        SQLiteDatabase sq=this.getReadableDatabase();
        String [] columns={TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASS};
        Cursor cr=sq.query(TableData.TableInfo.TABLE_NAME,columns,null,null,null,null,null);
        Log.d("DBOpr","row selected");
        return cr;
    }

}

