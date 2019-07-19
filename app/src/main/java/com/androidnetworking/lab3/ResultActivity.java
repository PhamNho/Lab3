package com.androidnetworking.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView tvDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvDung = findViewById(R.id.tvDung);

        Intent intent = getIntent();
        String diem = intent.getStringExtra("diem");
        tvDung.setText(diem);
    }
}
