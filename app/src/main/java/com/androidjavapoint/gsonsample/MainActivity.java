package com.androidjavapoint.gsonsample;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String JSON_FILE_NAME = "employee_details.json";
    private String mJsonString;
    private TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_load_json).setOnClickListener(this);
        findViewById(R.id.btn_parse_json).setOnClickListener(this);

        mTextResult = (TextView) findViewById(R.id.text_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load_json:
                loadJson();
                break;

            case R.id.btn_parse_json:
                parseJson();
                break;
        }
    }

    private void loadJson() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                mJsonString = Utils.loadJsonFromAsset(MainActivity.this, JSON_FILE_NAME);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(MainActivity.this, "JSON load successfully", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void parseJson() {
        if (mJsonString != null) {
            Gson gson = new Gson();
            Employee employee = gson.fromJson(mJsonString, Employee.class);
            PersonalDetails personalDetails = employee.personalDetails;

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("Employee Details:");
            resultBuilder.append("\n");
            resultBuilder.append("Id: " + employee.id);
            resultBuilder.append("\n");
            resultBuilder.append("Name: " + personalDetails.name);
            resultBuilder.append("\n");
            resultBuilder.append("Age: " + personalDetails.age);
            resultBuilder.append("\n");
            resultBuilder.append("blood Group: " + personalDetails.bloodGroup);

            mTextResult.setText(resultBuilder.toString());
        } else {
            Toast.makeText(this, "Please load JSON", Toast.LENGTH_SHORT).show();
        }
    }
}
