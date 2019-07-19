package com.androidnetworking.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView tvDung;
    private TextView tvDaLam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvDung = findViewById(R.id.tvDung);
        tvDaLam = findViewById(R.id.tvDaLam);

    }
}
