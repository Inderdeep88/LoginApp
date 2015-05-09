package com.mycompany.singhinderdeep.loginapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText et_user_name= (EditText) findViewById(R.id.editText3);
        final EditText et_user_pass= (EditText) findViewById(R.id.editText4);
        final EditText et_conf_pass= (EditText) findViewById(R.id.editText5);
        Button btn_Reg= (Button) findViewById(R.id.button6);
        final Context ctx=this;


        btn_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     //            UserInfo userInfo = new UserInfo(et_user_name.getText().toString(),et_user_pass.getText().toString());
                UserInfo userInfo=new UserInfo();
    //            userInfo.setUserPass(et_user_pass.getText().toString());
    //            userInfo.setUserName(et_user_name.getText().toString());
                String user_name=et_user_name.getText().toString();
                String user_pass=et_user_pass.getText().toString();
                String conf_pass = et_conf_pass.getText().toString();
                userInfo.userName=user_name;
                userInfo.userPass=user_pass;
               // if (!user_pass.equals(conf_pass)) {
                if(!userInfo.userPass.equals(conf_pass)){
                    Log.d("RegisterActivity", "passwords do not match");
                    Toast.makeText(getBaseContext(), "passwords do not match", Toast.LENGTH_LONG).show();
                    et_user_name.setText("");
                    et_user_pass.setText("");
                    et_conf_pass.setText("");
                    et_user_name.requestFocus();

                } else {
                    DatabaseOpr dbo = new DatabaseOpr(ctx);
                   // dbo.putUserInfo(user_name,user_pass);
                    dbo.putUserInfo(userInfo);
                    Toast.makeText(getBaseContext(), "Registration Success", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
