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
    public static final String TAG_DBOPR="DATABASEOPR";
    public static final String TAG_DB_SUCCESS="DB Successfully created";
    public static final String TAG_TAB_SUCCESS="Table Successfully created";
    public static final String TAG_CONN_CLOSE="Connection close";
    public static final String TAG_INS_SUCCESS="Insert Successful";
    public static final String TAG_UPD_SUCCESS="Update Successful";
    public static final String TAG_DEL_SUCCESS="Delete Successful";
    public static final String TAG_SEL_SUCCESS="Select Successful";
    public static final String TAG_SEL_FAILED="Select Failed";

    public long status;

    public DatabaseOpr(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG_DBOPR,TAG_DB_SUCCESS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUERY="CREATE TABLE "+ TABLE_REG_INFO+"("+KEY_USER_NAME+ " " + KEY_USER_NAME_TYPE + "," + KEY_USER_PASS+" "+KEY_USER_PASS_TYPE+");";
        db.execSQL(CREATE_QUERY);
        Log.d(TAG_DBOPR, TAG_TAB_SUCCESS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long putUserInfo(UserInfo usrinf)  {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_USER_NAME, usrinf.getUserName());
        values.put(KEY_USER_PASS, usrinf.getUserPass());
        try {
            // Insert row
            status = db.insert(TABLE_REG_INFO, null, values);
            Log.d(TAG_DBOPR, TAG_INS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
            Log.d(TAG_DBOPR, TAG_CONN_CLOSE);
        }
        return status;
    }

    public long updateUserPass(UserInfo usrinf) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_PASS, usrinf.getUserPass());
        try {
            // update row
            status = db.update(TABLE_REG_INFO, values, KEY_USER_NAME + " = ?", new String[]{usrinf.getUserName()});
            Log.d(TAG_DBOPR, TAG_UPD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
            Log.d(TAG_DBOPR, TAG_CONN_CLOSE);
        }
        return status;
    }

    public long deleteUserInfo(UserInfo usrinf) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, usrinf.getUserName());

        try {
            // delete row
            status = db.delete(TABLE_REG_INFO, KEY_USER_NAME + " = ?", new String[]{usrinf.getUserName()});
            Log.d(TAG_DBOPR, TAG_DEL_SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            db.close();
            Log.d(TAG_DBOPR, TAG_CONN_CLOSE);
        }
        return status;
    }

    public Cursor getUserInfoAll()  {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cr=null;
        String [] columns={KEY_USER_NAME, KEY_USER_PASS};

        try {
            cr = db.query(TABLE_REG_INFO, columns, null, null, null, null, null);
            if(cr!=null && cr.getCount()!=0)
                Log.d(TAG_DBOPR, TAG_SEL_SUCCESS);
            else
                Log.d(TAG_DBOPR, TAG_SEL_FAILED);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cr.close();
            db.close();
            Log.d(TAG_DBOPR, TAG_CONN_CLOSE);
        }
        return cr;
    }

    public String getUserInfo(String user_name)  {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cr=null;
        String user_pwd=null;
        String [] columns={KEY_USER_NAME, KEY_USER_PASS};

        try {

            cr=db.query(TABLE_REG_INFO,columns,KEY_USER_NAME+"=?",new String[]{user_name},null,null,null);

            if(cr!=null && cr.getCount()==1) {
                Log.d(TAG_DBOPR, TAG_SEL_SUCCESS);
                cr.moveToFirst();
                user_pwd=cr.getString(1);
            }
            else
                Log.d(TAG_DBOPR, TAG_SEL_FAILED);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cr.close();
            db.close();
            Log.d(TAG_DBOPR, TAG_CONN_CLOSE);
        }
        return user_pwd;
    }
}

