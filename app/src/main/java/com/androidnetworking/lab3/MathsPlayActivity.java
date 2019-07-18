package com.androidnetworking.lab3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_play);

        HttpGetTask httpGetTask = new HttpGetTask();

        httpGetTask.execute("http://dotplays.com/android/lab3.json");
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
                JSONArray root = new JSONArray(s);

                Log.e("Root size", String.valueOf(root.length()));
                for (int i = 0; i < root.length(); i++) {

                    JSONObject post = root.getJSONObject(i);
                    int id = post.getInt("id");

                    Log.e("id", String.valueOf(id));

                    String date = post.getString("date");
                    Log.e("date", String.valueOf(date));

                    JSONObject title = post.getJSONObject("title");

                    String rendered = title.getString("rendered");

                    Log.e("title", rendered);


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
