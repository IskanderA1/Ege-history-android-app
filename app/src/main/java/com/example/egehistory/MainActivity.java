package com.example.egehistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_NAME = "Score";

    private TextView mScoreTextView;
    private Button mLearnButton;

    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        mLearnButton = (Button) findViewById(R.id.StartButton);
        mScoreTextView = (TextView) findViewById(R.id.scoreTextView);

        if(mSettings.contains(APP_PREFERENCES_NAME)){
            mScoreTextView.setText("Твои знания: "+ mSettings.getInt(APP_PREFERENCES_NAME,0)+"/100");
        }

        mLearnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });

    }
}