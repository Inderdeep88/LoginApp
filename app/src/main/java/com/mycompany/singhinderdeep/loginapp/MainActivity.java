package com.mycompany.singhinderdeep.loginapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    Button buttonLogin, buttonUpdate, buttonDelete;
    UserInfo userInfo;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin=(Button)findViewById(R.id.maButtonLogin);
        buttonUpdate=(Button)findViewById(R.id.maButtonUpdate);
        buttonDelete=(Button)findViewById(R.id.maButtonDelete);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=new Bundle();
                b.putInt(UserInfo.BUNDLE_ORIGIN_KEY,UserInfo.BUNDLE_ORIGIN_LOGIN);
                Intent intent=new Intent(ctx,LoginActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt(UserInfo.BUNDLE_ORIGIN_KEY, UserInfo.BUNDLE_ORIGIN_UPDATE);
                Intent intent = new Intent(ctx, LoginActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=new Bundle();
                b.putInt(UserInfo.BUNDLE_ORIGIN_KEY,UserInfo.BUNDLE_ORIGIN_DELETE);
                Intent intent=new Intent(ctx,LoginActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void register(View v)
    {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
