package com.example.axent.sgtest;

import android.content.Intent;
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
        Intent intent = getIntent();
        mail_view.setText(intent.getStringExtra("user_mail"));

        Button exit_but = (Button) findViewById(R.id.inf_exit_butt);
        exit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inform.this, Main.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
