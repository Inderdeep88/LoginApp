package com.mycompany.singhinderdeep.loginapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DeleteActivity extends Activity {

    EditText et_pass;
    TextView tv;
    Button butDel;
    String name,pass;
    Context ctx=this;
    UserInfo userInfo;
    long status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        name = getIntent().getExtras().getString(UserInfo.BUNDLE_NAME_KEY);
        pass = getIntent().getExtras().getString(UserInfo.BUNDLE_PASS_KEY);

        tv=(TextView)findViewById(R.id.textView);
        et_pass=(EditText)findViewById(R.id.editText9);
        butDel=(Button)findViewById(R.id.button8);

        butDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPwd=et_pass.getText().toString();

                if (strPwd.isEmpty()) {
                        Toast.makeText(getBaseContext(), "Password is empty \nTry Again...", Toast.LENGTH_LONG).show();
                }
                else if (strPwd.equals(pass)){
                     //call delete db helper
                    DatabaseOpr dop = new DatabaseOpr(ctx);
                    userInfo=new UserInfo();
                    userInfo.setUserName(name);
                    status=dop.deleteUserInfo(userInfo);
                    if(status==1){
                        Toast.makeText(getBaseContext(), "User Info deleted successfully", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Failed to update Password", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                     Toast.makeText(getBaseContext(), "Password is Incorrect \nTry Again...", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
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
