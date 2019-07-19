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
    private Button btnNextQuestion;
    int i = 0;
    int diem = 0;

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
        btnNextQuestion = findViewById(R.id.btnNextQuestion);

        HttpGetTask httpGetTask = new HttpGetTask();
        httpGetTask.execute("http://dotplays.com/android/lab3.json");
    }

    public void NextQuestion(View view) {
        Intent intent = new Intent(this, MathsPlayActivity.class);
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

                Log.e("root======", String.valueOf(root));

                JSONObject quiz = root.getJSONObject("quiz");

                Log.e("quiz======", String.valueOf(quiz));

                final JSONArray maths = quiz.getJSONArray("maths");

                Log.e("maths======", String.valueOf(maths));

                final JSONObject post = maths.getJSONObject(i);

                tvCau.setText(i + 1 + "");

                String cauHoi = post.getString("question");

                final String dapAn = post.getString("answer");

                tvCauHoi.setText(cauHoi);

                JSONArray options = post.getJSONArray("options");

                final String A = options.getString(0);

                tvDapAnA.setText(A);
                tvDapAnA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (A.equals(dapAn)) {
                            diem = diem + 1;
                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                final String B = options.getString(1);

                tvDapAnB.setText(B);
                tvDapAnB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (B.equals(dapAn)) {
                            diem = diem + 1;
                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                final String C = options.getString(2);

                tvDapAnC.setText(C);
                tvDapAnC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (C.equals(dapAn)) {
                            diem = diem + 1;
                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                final String D = options.getString(3);

                tvDapAnD.setText(D);
                tvDapAnD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (D.equals(dapAn)) {
                            diem = diem + 1;
                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnNextQuestion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        i++;
                        if ((i + 1) == post.length()) {
                            Intent intent = new Intent(MathsPlayActivity.this, ResultActivity.class);
                            intent.putExtra("diem", diem);
                            startActivity(intent);
                        } else {
                            try {
                                    JSONObject post = maths.getJSONObject(i);

                                tvCau.setText(i + 1 + "");

                                String cauHoi = post.getString("question");

                                final String dapAn = post.getString("answer");

                                tvCauHoi.setText(cauHoi);

                                JSONArray options = post.getJSONArray("options");

                                final String A = options.getString(0);

                                tvDapAnA.setText(A);
                                tvDapAnA.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (A.equals(dapAn)) {
                                            diem++;
                                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                                        } else {

                                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                final String B = options.getString(1);

                                tvDapAnB.setText(B);
                                tvDapAnB.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (B.equals(dapAn)) {
                                            diem = diem + 1;
                                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                                        } else {

                                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                final String C = options.getString(2);

                                tvDapAnC.setText(C);
                                tvDapAnC.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (C.equals(dapAn)) {
                                            diem = diem + 1;
                                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                                        } else {

                                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                final String D = options.getString(3);

                                tvDapAnD.setText(D);
                                tvDapAnD.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (D.equals(dapAn)) {
                                            diem = diem + 1;
                                            Toast.makeText(MathsPlayActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                                        } else {

                                            Toast.makeText(MathsPlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
