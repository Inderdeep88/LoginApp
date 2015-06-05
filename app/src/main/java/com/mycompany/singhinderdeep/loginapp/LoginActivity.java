package com.mycompany.singhinderdeep.loginapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

    Context ctx=this;
    boolean login_status = false;
    Button bt_login;
    int origin;
    UserInfo userInfo=null;
    public static final String LOGIN_TAG="Login Activity";

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
                et_login_user_name = (EditText) findViewById(R.id.laETName);
                et_login_user_pass = (EditText) findViewById(R.id.laETPassword);
                if (et_login_user_name.getText().toString().isEmpty() || et_login_user_pass.getText().toString().isEmpty())
                {
                    Log.d(LOGIN_TAG, "empty");
                    Toast.makeText(getBaseContext(), "Login name or password empty \nTry Again...", Toast.LENGTH_LONG).show();
                }
                else
                {
                    userInfo = new UserInfo();
                    userInfo.setUserName(et_login_user_name.getText().toString());
                    userInfo.setUserPass(et_login_user_pass.getText().toString());
                    DatabaseOpr dop = new DatabaseOpr(ctx);
                    String fetchedPass = dop.getUserInfo(userInfo.getUserName());
                    Log.d(LOGIN_TAG, "After getUserInfo()");
                    if (userInfo.getUserPass().equals(fetchedPass))
                    {
                        Log.d(LOGIN_TAG, "inside if");
                        Toast.makeText(getBaseContext(), "Login Successful \n Welcome " + userInfo.getUserName(), Toast.LENGTH_LONG).show();
                        login_status=true;
                    }
                    else
                    {
                        Log.d(LOGIN_TAG, "inside else");
                        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
                    }
                    if (login_status && origin==UserInfo.BUNDLE_ORIGIN_UPDATE)
                    {
                        Log.d("Login Activity", "inside if2");
                        Intent in = new Intent(getBaseContext(),UpdateActivity.class);
                        Bundle b=new Bundle();
                        b.putString(UserInfo.BUNDLE_NAME_KEY, userInfo.getUserName());
                        b.putString(UserInfo.BUNDLE_PASS_KEY, userInfo.getUserPass());
                        in.putExtras(b);
                        startActivity(in);
                    }
                    else if(login_status && origin==UserInfo.BUNDLE_ORIGIN_DELETE)
                    {
                        Log.d("Login Activity", "inside if2");
                        Intent i = new Intent(ctx,DeleteActivity.class);
                        Bundle b=new Bundle();
                        b.putString(UserInfo.BUNDLE_NAME_KEY, userInfo.getUserName());
                        b.putString(UserInfo.BUNDLE_PASS_KEY, userInfo.getUserPass());
                        i.putExtras(b);
                        startActivity(i);
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
