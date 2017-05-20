package com.example.axent.sgtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Inform extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inform_screen);

        TextView mail_view = (TextView) findViewById(R.id.inf_mail_text);
        final SharedPreferences pref = getSharedPreferences("User", 0);
        final SharedPreferences.Editor editor = pref.edit();
        String mail = pref.getString("user_mail", null);
        mail_view.setText(mail);

        Button exit_but = (Button) findViewById(R.id.inf_exit_butt);
        exit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("user_mail");
                editor.commit();
                Intent intent = new Intent(Inform.this, Main.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
