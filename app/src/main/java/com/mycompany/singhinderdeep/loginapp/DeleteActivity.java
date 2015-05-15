package com.mycompany.singhinderdeep.loginapp;

import android.app.Activity;
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
    String message="Hi User !";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        String name = getIntent().getExtras().getString("user_name");
        final String pass=getIntent().getExtras().getString("user_pass");

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
