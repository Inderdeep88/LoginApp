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

    Button buttonLogin, buttonRegister, buttonUpdate, buttonDelete;
    int origin;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login=(Button)findViewById(R.id.button);
        Button register=(Button)findViewById(R.id.button2);
        Button update=(Button)findViewById(R.id.button3);
        Button delete=(Button)findViewById(R.id.button4);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                origin=1;    // origin 1 means it is called from login button
                Bundle b=new Bundle();
                b.putInt("origin",origin);
                Intent intent=new Intent(ctx,LoginActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
//
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                origin=2;    // origin 2 means it is called from register button
//                Bundle b=new Bundle();
//                b.putInt("origin",origin);
//                Intent intent=new Intent(ctx,RegisterActivity.class);
//                intent.putExtras(b);
//                startActivity(intent);
//            }
//        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                origin = 2;    // origin 2 means it is called from Update button
                Bundle b = new Bundle();
                b.putInt("origin", origin);
                Intent intent = new Intent(ctx, LoginActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                origin=3;    // origin 3 means it is called from delete button
                Bundle b=new Bundle();
                b.putInt("origin",origin);
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
