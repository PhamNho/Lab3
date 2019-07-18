package com.androidnetworking.lab3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class SportPlayActivity extends AppCompatActivity {
    private TextView tvCau;
    private TextView tvCauHoi;
    private TextView tvDapAnA;
    private TextView tvDapAnB;
    private TextView tvDapAnC;
    private TextView tvDapAnD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_play);

        tvCau = findViewById(R.id.tvCau);
        tvCauHoi = findViewById(R.id.tvCauHoi);
        tvDapAnA = findViewById(R.id.tvDapAnA);
        tvDapAnB = findViewById(R.id.tvDapAnB);
        tvDapAnC = findViewById(R.id.tvDapAnC);
        tvDapAnD = findViewById(R.id.tvDapAnD);

        HttpGetTask httpGetTask = new HttpGetTask();
        httpGetTask.execute("http://dotplays.com/android/lab3.json");
    }

    public void loadNextQuestion(View view) {

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

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                JSONObject root = new JSONObject(s);

                JSONObject quiz = root.getJSONObject("quiz");

                Log.e("quiz", String.valueOf(quiz));

                JSONObject sport = quiz.getJSONObject("sport");

                Log.e("sport", String.valueOf(sport.length()));
                for (int i = 0; i < sport.length(); i++) {
                    JSONObject q = sport.getJSONObject("q" + (i + 1));

                    tvCau.setText(i+1+"");

                    String cauHoi = q.getString("question");

                    tvCauHoi.setText(cauHoi);

                    JSONArray options = q.getJSONArray("options");

                    String A = options.getString(0);

                    tvDapAnA.setText(A);

                    String B = options.getString(1);

                    tvDapAnB.setText(B);

                    String C = options.getString(2);

                    tvDapAnC.setText(C);

                    String D = options.getString(3);

                    tvDapAnD.setText(D);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
