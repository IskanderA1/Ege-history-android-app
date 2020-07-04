package com.example.egehistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionActivity extends AppCompatActivity {

    private Button mNextButton;
    private TextView mQuestionTextView;
    private TextView mImportantPointTextView;
    private TextView mScoreTextView;
    private EditText mStartEditText;
    private EditText mFinishEditText;


    private DbModelClass DbModelClass;
    private MyDbClass mDbClass;
    private ArrayList<DbModelClass> mDbModelClassArrayList;
    private SharedPreferences mSettings;

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_NAME = "Score";

    private int curr;
    private int Score;
    private int scoreQuestion = 1;

    SwipeFlingAdapterView flingContainer;
    QuestionAdapter arrayAdapter;
    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().hide();

        mDbClass = new MyDbClass(this);
        mDbModelClassArrayList = new ArrayList<>();
        mDbModelClassArrayList = mDbClass.getAllData();

        Log.d("Test",String.valueOf(mDbModelClassArrayList.size()));
        //Collections.shuffle(mDbModelClassArrayList);
        arrayAdapter = new QuestionAdapter(this, mDbModelClassArrayList);


        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);




        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                mDbModelClassArrayList.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(QuestionActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(QuestionActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });



        /*mNextButton = (Button) findViewById(R.id.NextButton);
        mQuestionTextView = (TextView) findViewById(R.id.nameMonarch);
        //mImportantPointTextView = (TextView) findViewById(R.id.ImportantPoints);
        mScoreTextView = (TextView) findViewById(R.id.score);
        mStartEditText = (EditText) findViewById(R.id.textInputEditStart);
        mFinishEditText = (EditText) findViewById(R.id.textInputEditFinish);*/
/*        mStartEditText.setInputType(InputType.TYPE_CLASS_PHONE );
        mFinishEditText.setInputType(InputType.TYPE_CLASS_PHONE);*/


/*
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);



        if(mSettings.contains(APP_PREFERENCES_NAME)) {
            mScoreTextView.setText(String.valueOf(mSettings.getInt(APP_PREFERENCES_NAME, 0)));
        }



*/

/*        if(mDbModelClassArrayList.size()>curr){
            randomQuestion();
            mQuestionTextView.setText(DbModelClass.getName());
            //mImportantPointTextView.setText(DbModelClass.getDescription());
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imm.hideSoftInputFromWindow(mNextButton.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    if(mSettings.contains(APP_PREFERENCES_NAME)) {
                        mScoreTextView.setText(String.valueOf(mSettings.getInt(APP_PREFERENCES_NAME, 0)));
                    }
                    if(mDbModelClassArrayList.size()-1>curr) {
                        mStartEditText.clearFocus();
                        Log.d("TestQuestion", mStartEditText.getText().toString() + "==" + DbModelClass.getStartDate());
                        if (mStartEditText.getText().toString().replaceAll(" ", "").equals(DbModelClass.getStartDate()) && mFinishEditText.getText().toString().replaceAll(" ", "").equals(DbModelClass.getFinishDate())) {
                            curr++;
                            mStartEditText.setText("");
                            mFinishEditText.setText("");
                            randomQuestion();
                            mQuestionTextView.setText(DbModelClass.getName());
                            //mImportantPointTextView.setText(DbModelClass.getDescription());
                            Score+=scoreQuestion;
                            scoreQuestion = 1;

                            Log.d("SCOREEGE", String.valueOf(Score));
                            mScoreTextView.setText(String.valueOf(Score));

                        } else if (mStartEditText.getText().toString().replaceAll(" ", "").equals(DbModelClass.getStartDate())) {
                            Toast.makeText(QuestionActivity.this, "Конец правления: " + DbModelClass.getFinishDate(), Toast.LENGTH_LONG).show();
                            setScoreQuestion(2);
                            mScoreTextView.setText(String.valueOf(Score));

                        } else if (mFinishEditText.getText().toString().replaceAll(" ", "").equals(DbModelClass.getFinishDate())) {
                            Toast.makeText(QuestionActivity.this, "Начало правления: " + DbModelClass.getStartDate(), Toast.LENGTH_LONG).show();
                            setScoreQuestion(2);
                            mScoreTextView.setText(String.valueOf(Score));
                        }else {
                            Toast.makeText(QuestionActivity.this, DbModelClass.getStartDate()+"-" + DbModelClass.getFinishDate(), Toast.LENGTH_LONG).show();
                            setScoreQuestion(3);
                            mScoreTextView.setText(String.valueOf(Score));
                        }
                    }else{

                    }
                }
            });
        }else{
            Toast.makeText(this,"Нет вопросов", Toast.LENGTH_SHORT).show();
        }
*/

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_NAME, Score);
        editor.apply();

    }


    private void randomQuestion(){
        DbModelClass = mDbModelClassArrayList.get(curr);
    }

    public void setScoreQuestion(int i) {
        if(scoreQuestion-i>=-2&&Score+(scoreQuestion-i)>=0&&Score<100){
            scoreQuestion-=i;
        }else{
            scoreQuestion = 0;
        }
    }
}