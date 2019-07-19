package com.androidnetworking.lab3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MathsPlayActivity extends AppCompatActivity {
    private TextView tvCau;
    private TextView tvCauHoi;
    private TextView tvDapAnA;
    private TextView tvDapAnB;
    private TextView tvDapAnC;
    private TextView tvDapAnD;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_play);

        tvCau = findViewById(R.id.tvCau);
        tvCauHoi = findViewById(R.id.tvCauHoi);
        tvDapAnA = findViewById(R.id.tvDapAnA);
        tvDapAnB = findViewById(R.id.tvDapAnB);
        tvDapAnC = findViewById(R.id.tvDapAnC);
        tvDapAnD = findViewById(R.id.tvDapAnD);

        HttpGetTask httpGetTask = new HttpGetTask();
        httpGetTask.execute("http://dotplays.com/android/lab3.json");
    }

    public void NextQuestion(View view) {
        Intent intent= new Intent(this, MathsPlayActivity.class);
        startActivity(intent);
    }

    public class HttpGetTask extends AsyncTask<String, Long, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                String data = "";
                while (scanner.hasNext()) {
                    data = data + scanner.nextLine();
                }
                scanner.close();
                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                JSONObject root = new JSONObject(s);

                JSONObject quiz = root.getJSONObject("quiz");

                JSONObject maths = quiz.getJSONObject("maths");

                JSONObject q = maths.getJSONObject("q" + (i));

                tvCau.setText(i + "");

                String cauHoi = q.getString("question");

                final String dapAn = q.getString("answer");

                tvCauHoi.setText(cauHoi);

                JSONArray options = q.getJSONArray("options");

                final String A = options.getString(0);

                tvDapAnA.setText(A);
                tvDapAnA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (A.equals(dapAn)){
                            i++;
                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                        }else {
                            i++;
                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                final String B = options.getString(1);

                tvDapAnB.setText(B);
                tvDapAnB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (B.equals(dapAn)){
                            i++;
                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                        }else {
                            i++;
                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                final String C = options.getString(2);

                tvDapAnC.setText(C);
                tvDapAnC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (C.equals(dapAn)){
                            i++;
                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                        }else {
                            i++;
                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                final String D = options.getString(3);

                tvDapAnD.setText(D);
                tvDapAnD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (D.equals(dapAn)){
                            i++;
                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                        }else {
                            i++;
                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
