package com.example.admin.recyclerview;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.drm.ProcessedData;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int DIALOG_KEY = 1;
    private Button btnUpdated;
    private ProgressBar pgbLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnUpdated = (Button) findViewById(R.id.btnUpdated);

        pgbLoad = (ProgressBar) findViewById(R.id.pgbLoad);
        pgbLoad.setVisibility(View.INVISIBLE);

        btnUpdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskDemo ast = new AsyncTaskDemo();
                ast.execute();

            }
        });
    }


    private class AsyncTaskDemo extends AsyncTask<String, Integer , String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Bắt Đầu", Toast.LENGTH_SHORT).show();
            pgbLoad.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            for(int i = 0; i < 20; i++){
                try {
                    Thread.sleep(100);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Hoàn tất";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            pgbLoad.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            pgbLoad.setProgress(values[0]);
        }
    }
}
