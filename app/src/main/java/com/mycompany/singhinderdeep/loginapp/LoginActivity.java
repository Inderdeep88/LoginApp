package com.mycompany.singhinderdeep.loginapp;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Context ctx=this;

        final EditText et_login_user_name=(EditText)findViewById(R.id.editText);
        final EditText et_login_user_pass=(EditText)findViewById(R.id.editText2);
        final Button bt_login=(Button)findViewById(R.id.button5);
        final UserInfo userInfo = new UserInfo();
        //userInfo.setUserName(et_login_user_name.getText().toString());
        //userInfo.setUserPass(et_login_user_pass.getText().toString());

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // final String un=et_login_user_name.getText().toString();
               // final String up=et_login_user_pass.getText().toString();
                userInfo.userName=et_login_user_name.getText().toString();
                userInfo.userPass=et_login_user_pass.getText().toString();
              //  if (un.isEmpty()|| up.isEmpty())
                if(userInfo.userPass.isEmpty()||userInfo.userName.isEmpty())
                {
                    Log.d("inside","empty");
                    Toast.makeText(getBaseContext(), "Login name or password empty Try Again...", Toast.LENGTH_LONG).show();

                }else {


                    DatabaseOpr dop = new DatabaseOpr(ctx);
                    Cursor cr = dop.getUserInfo();
                    Log.d("Login Activity", "After getUserInfo()");
                    int count = cr.getCount();
                    if (count == 0) {
                        Toast.makeText(getBaseContext(), "DB is empty \nCreate new registration...", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                    cr.moveToFirst();
                    Log.d("Login Activity", cr.getString(0));
                    Log.d("Login Activity", cr.getString(1));
                    Log.d("Login Activity", "Check Check");

                    //Log.d("Login Activity", un);
                    //Log.d("Login Activity", up);
                    boolean login_status = false;
                    String name = "";
                    do {
                        Log.d("Login Activity", "inside loop");
                        Log.d("Login Activity", cr.getString(0));
                        Log.d("Login Activity", cr.getString(1));

                      //  if (un.equals(cr.getString(0)) && up.equals(cr.getString(1))) {
                        if (userInfo.userName.equals(cr.getString(0)) && userInfo.userPass.equals(cr.getString(1))) {
                            Log.d("Login Activity", "inside if1");
                            login_status = true;
                            name = cr.getString(0);
                            break;
                        }
                    } while (cr.moveToNext());
                    if (login_status == true) {
                        Log.d("Login Activity", "inside if2");
                        Toast.makeText(getBaseContext(), "Login Successful \n Welcome " + name, Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Log.d("Login Activity", "inside else");
                        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
