package com.example.axent.sgtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    Button registration_button;
    Button login_button;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        SharedPreferences pref = getSharedPreferences("User", 0);
        final SharedPreferences.Editor editor = pref.edit();
        if (pref.getString("user_mail",null) != null) {
            Intent intent = new Intent(Main.this, Inform.class);
            startActivity(intent);
        } else {
            //**
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new DBHelper(Main.this,"UsersDB",  1 );

        //registration button
        registration_button = (Button) findViewById(R.id.log_registration_but);
        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                Intent intent = new Intent(Main.this, Registration.class);
                startActivity(intent);
                }
         });

        //login button
        login_button = (Button) findViewById(R.id.log_login_but);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MD5Crypt md5Crypt = new MD5Crypt();

                EditText mail_edit = (EditText) findViewById(R.id.log_mail_edit);
                EditText password_edit = (EditText) findViewById(R.id.log_password_edit);
                String mail = mail_edit.getText().toString();
                String password = password_edit.getText().toString();

                if (mail.isEmpty() & password.isEmpty() ) {
                    Toast error_mes = Toast.makeText(Main.this, "Empty fields", Toast.LENGTH_LONG);
                    error_mes.show();
                } else {
                    if (dbHelper.checkMail(mail) == 0) {
                        Toast error_mes = Toast.makeText(Main.this, "User not exist, try another, or register new", Toast.LENGTH_LONG);
                        error_mes.show();
                    } else {
                        if (md5Crypt.md5Custom(password).equals(dbHelper.getPassword(mail))) {
                            Toast succses_mes = Toast.makeText(Main.this, "Login sucsessful", Toast.LENGTH_LONG);
                            succses_mes.show();

                            Intent intent = new Intent(Main.this, Inform.class);
                            editor.putString("user_mail", mail);
                            editor.commit();

                            startActivity(intent);
                            finish();
                        } else {
                            Toast error_password_mes = Toast.makeText(Main.this, "Wrong password", Toast.LENGTH_LONG);
                            error_password_mes.show();
                        }
                    }
                    ;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_screen, menu);
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
