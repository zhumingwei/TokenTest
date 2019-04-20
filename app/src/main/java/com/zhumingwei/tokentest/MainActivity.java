package com.zhumingwei.tokentest;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener((v)->{
            startActivity(new Intent(this,SecondActivity.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
