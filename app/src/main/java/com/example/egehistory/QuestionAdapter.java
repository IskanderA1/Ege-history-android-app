package com.example.egehistory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class QuestionAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<DbModelClass> objects;
    SwipeFlingAdapterView flingContainer;
    public QuestionAdapter(Context context, ArrayList<DbModelClass> dbModelClassArrayList,SwipeFlingAdapterView flingContainer) {
        this.flingContainer = flingContainer;
        ctx = context;
        objects = dbModelClassArrayList;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();

    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }


    // пункт списка
    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Button b1 = view.findViewById(R.id.buttonChoiceFirst);
        Button b2 = view.findViewById(R.id.buttonChoiceSecond);
        Button b3 = view.findViewById(R.id.buttonChoiceThird);
        Button b4 = view.findViewById(R.id.buttonChoiceFourth);

        class AnswerButtonClass{
            private boolean answerTrue;
            private String textAnswer;
            public AnswerButtonClass(boolean answerTrue, String textAnswer) {
                this.answerTrue = answerTrue;
                this.textAnswer = textAnswer;
            }
            public boolean isAnswerTrue() {
                return answerTrue;
            }
            public String getTextAnswer() {
                return textAnswer;
            }
        }


        DbModelClass  question = objects.get(position);
        ((TextView) view.findViewById(R.id.MonarchNameTextView)).setText(question.getName());



        Transformation transformation = new RoundedCornersTransformation(30, 0);
        Picasso.get().load("file:///android_asset/imageMonarch/"+question.getImage()).transform(transformation).into(imageView);

        ArrayList<AnswerButtonClass> buttonTextArrayList = new ArrayList<AnswerButtonClass>();
        buttonTextArrayList.add(new AnswerButtonClass(true,question.getTrueAnswer()));
        buttonTextArrayList.add(new AnswerButtonClass(false,question.getFalseAnswer(0)));
        buttonTextArrayList.add(new AnswerButtonClass(false,question.getFalseAnswer(1)));
        buttonTextArrayList.add(new AnswerButtonClass(false,question.getFalseAnswer(2)));
        //Collections.shuffle(buttonTextArrayList);
        b1.setText(buttonTextArrayList.get(0).getTextAnswer());
        b2.setText(buttonTextArrayList.get(1).getTextAnswer());
        b3.setText(buttonTextArrayList.get(2).getTextAnswer());
        b4.setText(buttonTextArrayList.get(3).getTextAnswer());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonTextArrayList.get(0).isAnswerTrue()){
                    flingContainer.getTopCardListener().selectRight();
                }else {
                    flingContainer.getTopCardListener().selectLeft();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonTextArrayList.get(1).isAnswerTrue()){
                    flingContainer.getTopCardListener().selectRight();
                }else {
                    flingContainer.getTopCardListener().selectLeft();
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonTextArrayList.get(2).isAnswerTrue()){
                    flingContainer.getTopCardListener().selectRight();
                }else {
                    flingContainer.getTopCardListener().selectLeft();
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonTextArrayList.get(3).isAnswerTrue()){
                    flingContainer.getTopCardListener().selectRight();
                }else {
                    flingContainer.getTopCardListener().selectLeft();
                }
            }
        });
        return view;
    }


}
