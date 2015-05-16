package com.mycompany.singhinderdeep.loginapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

    Context ctx=this;
    boolean login_status = false;
    String name="";
    String pass="";
    Button bt_login;
    int origin;

    EditText et_login_user_name, et_login_user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        origin = getIntent().getExtras().getInt(UserInfo.BUNDLE_ORIGIN_KEY);
        bt_login=(Button)findViewById(R.id.laButtonLogin);

        bt_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(origin==UserInfo.BUNDLE_ORIGIN_LOGIN)
                {
                    UserInfo userInfo = new UserInfo();
                    et_login_user_name = (EditText) findViewById(R.id.laETName);
                    et_login_user_pass = (EditText) findViewById(R.id.laETPassword);
                    userInfo.setUserName(et_login_user_name.getText().toString());
                    userInfo.setUserPass(et_login_user_pass.getText().toString());

                    if (userInfo.getUserPass().isEmpty() || userInfo.getUserName().isEmpty())
                    {
                        Log.d("inside", "empty");
                        Toast.makeText(getBaseContext(), "Login name or password empty \nTry Again...", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        DatabaseOpr dop = new DatabaseOpr(ctx);
                        Cursor cr = dop.getUserInfo();
                        Log.d("Login Activity", "After getUserInfo()");
                        int count = cr.getCount();
                        if (count == 0)
                        {
                            Toast.makeText(getBaseContext(), "DB is empty \nCreate new registration...", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                        cr.moveToFirst();
                        Log.d("Login Activity", cr.getString(0));
                        Log.d("Login Activity", cr.getString(1));
                        Log.d("Login Activity", "Check Check");

                        do
                        {
                            Log.d("Login Activity", "inside loop");
                            Log.d("Login Activity", cr.getString(0));
                            Log.d("Login Activity", cr.getString(1));

                            if (userInfo.userName.equals(cr.getString(0)) && userInfo.userPass.equals(cr.getString(1)))
                            {
                                Log.d("Login Activity", "inside if1");
                                login_status = true;
                                name = cr.getString(0);
                                break;
                            }
                        } while (cr.moveToNext());
                        if (login_status)
                        {
                            Log.d("Login Activity", "inside if2");
                            Toast.makeText(getBaseContext(), "Login Successful \n Welcome " + name, Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Log.d("Login Activity", "inside else");
                            Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(origin==UserInfo.BUNDLE_ORIGIN_UPDATE)
                {
                    UserInfo userInfo = new UserInfo();
                    et_login_user_name = (EditText) findViewById(R.id.laETName);
                    et_login_user_pass = (EditText) findViewById(R.id.laETPassword);
                    userInfo.setUserName(et_login_user_name.getText().toString());
                    userInfo.setUserPass(et_login_user_pass.getText().toString());

                    if (userInfo.userPass.isEmpty() && userInfo.userName.isEmpty())
                    {
                        Log.d("inside", "empty");
                        Toast.makeText(getBaseContext(), "Login name or password empty Try Again...", Toast.LENGTH_LONG).show();
                    }
                    else if (userInfo.userName.isEmpty()){
                        Toast.makeText(getBaseContext(), "Login name is empty Try Again...", Toast.LENGTH_LONG).show();
                    }
                    else if (userInfo.userPass.isEmpty()){
                        Toast.makeText(getBaseContext(), "Password is empty Try Again...", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        DatabaseOpr dop = new DatabaseOpr(ctx);
                        Cursor cr = dop.getUserInfo();
                        if(cr==null){
                            Toast.makeText(getBaseContext(), "Not able to fetch user from DB...", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                        Log.d("Login Activity", "After getUserInfo()");
                        int count = cr.getCount();
                        if (count == 0)
                        {
                            Toast.makeText(getBaseContext(), "DB is empty \nCreate new registration...", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                        cr.moveToFirst();
                        Log.d("Login Activity", cr.getString(0));
                        Log.d("Login Activity", cr.getString(1));
                        do
                        {
                            Log.d("Login Activity", "inside loop");
                            Log.d("Login Activity", cr.getString(0));
                            Log.d("Login Activity", cr.getString(1));

                            if (userInfo.userName.equals(cr.getString(0)) && userInfo.userPass.equals(cr.getString(1)))
                            {
                                Log.d("Login Activity", "inside if1");
                                login_status = true;
                                name = cr.getString(0);
                                break;
                            }
                        } while (cr.moveToNext());
                        if (login_status)
                        {
                            Log.d("Login Activity", "inside if2");
                            Toast.makeText(getBaseContext(), "Login Successful \n Welcome " + name, Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ctx,UpdateActivity.class);
                            Bundle b=new Bundle();
                            b.putString("user_name", userInfo.userName);
                            b.putString("user_pass", userInfo.userPass);
                            in.putExtras(b);
                            startActivity(in);
                            finish();
                        }
                        else
                        {
                            Log.d("Login Activity", "inside else");
                            Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(origin==UserInfo.BUNDLE_ORIGIN_DELETE)
                {
                    UserInfo userInfo = new UserInfo();
                    EditText et_login_user_name = (EditText) findViewById(R.id.laETName);
                    EditText et_login_user_pass = (EditText) findViewById(R.id.laETPassword);
                    userInfo.setUserName(et_login_user_name.getText().toString());
                    userInfo.setUserPass(et_login_user_pass.getText().toString());

                    if (userInfo.userPass.isEmpty() || userInfo.userName.isEmpty())
                    {
                        Log.d("inside", "empty");
                        Toast.makeText(getBaseContext(), "Login name or password empty Try Again...", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        DatabaseOpr dop = new DatabaseOpr(ctx);
                        Cursor cr = dop.getUserInfo();
                        Log.d("Login Activity", "After getUserInfo()");
                        int count = cr.getCount();
                        if (count == 0)
                        {
                            Toast.makeText(getBaseContext(), "DB is empty \nCreate new registration...", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                        cr.moveToFirst();
                        Log.d("Login Activity", cr.getString(0));
                        Log.d("Login Activity", cr.getString(1));
                        Log.d("Login Activity", "Check Check");

                        do
                        {
                            Log.d("Login Activity", "inside loop");
                            Log.d("Login Activity", cr.getString(0));
                            Log.d("Login Activity", cr.getString(1));

                            if (userInfo.userName.equals(cr.getString(0)) && userInfo.userPass.equals(cr.getString(1)))
                            {
                                Log.d("Login Activity", "inside if1");
                                login_status = true;
                                name = cr.getString(0);
                                break;
                            }
                        } while (cr.moveToNext());
                        if (login_status)
                        {
                            Log.d("Login Activity", "inside if2");
                            Toast.makeText(getBaseContext(), "Login Successful \n Welcome " + name, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ctx,DeleteActivity.class);
                            Bundle b=new Bundle();
                            b.putString("user_name",userInfo.userName);
                            b.putString("user_pass",userInfo.userPass);
                            i.putExtras(b);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Log.d("Login Activity", "inside else");
                            Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
                        }
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
