package com.example.myapplication;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private EditText sco = null;
    private EditText move = null;
    private Button re = null;
    private Button restart = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Boardview b = new Boardview(this);
        re = (Button) findViewById(R.id.return1);
        restart = (Button) findViewById(R.id.restart);
        sco = (EditText) findViewById(R.id.sco);
        move = (EditText) findViewById(R.id.move);
        sco.setCursorVisible(false);
        move.setCursorVisible(false);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,MainActivity.class));


            }
        });


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Canvas c = new Canvas();
                b.onDraw(c);
            }
        });
    }
}
