package com.mycompany.singhinderdeep.loginapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateActivity extends Activity {

    EditText oldpwd,newpwd;
    Button butUpd;
    long status=0;
    String name,pass;
    UserInfo userInfo;
    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        oldpwd=(EditText)findViewById(R.id.uaETOldPassword);
        newpwd=(EditText)findViewById(R.id.uaETNewPassword);
        butUpd=(Button)findViewById(R.id.uaButtonUpdate);

        name=getIntent().getExtras().getString(UserInfo.BUNDLE_NAME_KEY);
        pass=getIntent().getExtras().getString(UserInfo.BUNDLE_PASS_KEY);

        butUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOldpwd=oldpwd.getText().toString();
                String strNewpwd=newpwd.getText().toString();
                boolean flag=true;

                if(!strOldpwd.equals(strNewpwd)) {
                    if (strOldpwd.isEmpty()) {
                        Toast.makeText(getBaseContext(), "Old password is empty \nTry Again...", Toast.LENGTH_LONG).show();
                        flag=false;
                    }
                    if (strNewpwd.isEmpty()) {
                        Toast.makeText(getBaseContext(), "New password is empty \nTry Again...", Toast.LENGTH_LONG).show();
                        flag=false;
                    }
                    if(flag){
                        if(pass.equals(strOldpwd)){
                            //call update db helper
                            DatabaseOpr dop = new DatabaseOpr(ctx);
                            userInfo=new UserInfo();
                            userInfo.setUserName(name);
                            userInfo.setUserPass(strNewpwd);
                            status=dop.updateUserPass(userInfo);
                            if(status!=0) {
                                Toast.makeText(getBaseContext(), "Password changed successfully", Toast.LENGTH_LONG).show();
                                finish();
                                return;
                            }
                            else {
                                Toast.makeText(getBaseContext(), "Failed to update Password", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getBaseContext(), "Old password is Incorrect \nTry Again...", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                else if(strNewpwd.isEmpty()) {
                    Toast.makeText(getBaseContext(), "New password and old password cannot be empty \nTry Again...", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getBaseContext(), "New password and old password cannot be same \nTry Again...", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update, menu);
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
