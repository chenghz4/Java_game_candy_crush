package com.example.myapplication;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.widget.*;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private TextView input = null;
    private Button bt0 = null;
    private Button bt1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        bt0 = (Button) findViewById(R.id.bt0);
        bt1 = (Button) findViewById(R.id.bt1);
        input = (TextView) findViewById(R.id.tx0);

        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,Main2Activity.class));


            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
    }

}
