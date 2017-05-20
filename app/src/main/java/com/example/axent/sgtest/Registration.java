package com.example.axent.sgtest;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.commons.validator.routines.EmailValidator;



public class Registration extends Activity {
    Button cancel_Button;
    Button confirm_Button;
    DBHelper dbHelper = new DBHelper(Registration.this, "UsersDB", 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);


        //cancel button on registration
        cancel_Button = (Button) findViewById(R.id.reg_cancel_but);
        cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Main.class);
                startActivity(intent);
                onStop();
            }
        });

        //confirm button on registration
        confirm_Button = (Button) findViewById(R.id.reg_confirm_but);
        confirm_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText mail_EditText = (EditText) findViewById(R.id.reg_email_edit);
                EditText password_EditText = (EditText) findViewById(R.id.reg_password_edit);
                String mail = mail_EditText.getText().toString();
                EmailValidator emailValidator = EmailValidator.getInstance();
                if (mail_EditText.length() > 6) {
                    if (password_EditText.length() > 4){
                        if (emailValidator.isValid(mail)) {
                            if (dbHelper.checkMail(mail) == 0){
                                SQLiteDatabase database = dbHelper.getWritableDatabase();
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(dbHelper.KEY_MAIL, mail_EditText.getText().toString());

                                MD5Crypt md5Crypt = new MD5Crypt();
                                contentValues.put(dbHelper.KEY_PASSWORD, MD5Crypt.md5Custom(password_EditText.getText().toString()));
                                database.insert(dbHelper.TABLE_USERS, null, contentValues);

                                Toast succses_mes = Toast.makeText(Registration.this, "Registration sucsessful", Toast.LENGTH_LONG);
                                succses_mes.show();

                                Intent intent = new Intent(Registration.this, Main.class);
                                startActivity(intent);
                                database.close();
                                finish();

                            } else{


                                Toast error_mes = Toast.makeText(Registration.this, "User already exist, try another", Toast.LENGTH_LONG);
                                error_mes.show();

                            }
                        } else {
                            Toast error_mes = Toast.makeText(Registration.this, "Email not valid", Toast.LENGTH_LONG);
                            error_mes.show();

                        }
                    } else {
                        Toast error_mes = Toast.makeText(Registration.this, "Password must be at least 4 characters long", Toast.LENGTH_LONG);
                        error_mes.show();
                    }

                } else {
                    Toast error_mes = Toast.makeText(Registration.this, "Email must be at least 6 characters long", Toast.LENGTH_LONG);
                    error_mes.show();
                }


                }

        });
    }

}
