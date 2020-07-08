package com.example.egehistory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;


public class QuestionAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<DbModelClass> objects;

    Vibrator vibrator;
    Resources res;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    View.OnClickListener mCorkyListener;
    long[] pattern = {0, 400};
    public QuestionAdapter(Context context, ArrayList<DbModelClass> dbModelClassArrayList) {
        ctx = context;
        objects = dbModelClassArrayList;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vibrator = (Vibrator) ctx
                .getSystemService(Context.VIBRATOR_SERVICE);
        res = ctx.getResources();
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
        View finalView = view;

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        b1 = view.findViewById(R.id.buttonChoiceFirst);
        b2 = view.findViewById(R.id.buttonChoiceSecond);
        b3 = view.findViewById(R.id.buttonChoiceThird);
        b4 = view.findViewById(R.id.buttonChoiceFourth);




        class AnswerButtonClass {
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


        DbModelClass question = objects.get(position);
        ((TextView) view.findViewById(R.id.MonarchNameTextView)).setText(question.getName());


        Transformation transformation = new RoundedCornersTransformation(30, 0);
        Picasso.get().load("file:///android_asset/imageMonarch/" + question.getImage()).transform(transformation).into(imageView);

        ArrayList<AnswerButtonClass> buttonTextArrayList = new ArrayList<AnswerButtonClass>();
        buttonTextArrayList.add(new AnswerButtonClass(true, question.getTrueAnswer()));
        buttonTextArrayList.add(new AnswerButtonClass(false, question.getFalseAnswer(0)));
        buttonTextArrayList.add(new AnswerButtonClass(false, question.getFalseAnswer(1)));
        buttonTextArrayList.add(new AnswerButtonClass(false, question.getFalseAnswer(2)));
        //Collections.shuffle(buttonTextArrayList);
        b1.setText(buttonTextArrayList.get(0).getTextAnswer());
        b2.setText(buttonTextArrayList.get(1).getTextAnswer());
        b3.setText(buttonTextArrayList.get(2).getTextAnswer());
        b4.setText(buttonTextArrayList.get(3).getTextAnswer());



        b1.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                if(buttonTextArrayList.get(0).isAnswerTrue()){
                    v.setBackground(res.getDrawable(R.drawable.custom_button_true));
                    b1.setTextColor(R.color.buttonTrue);
                    Log.d("TEST_BUTTON", "isWork");
                    blockButton(finalView);

                }else {
                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(pattern, -1);
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonTextArrayList.get(1).isAnswerTrue()) {
                    b2.setBackground(res.getDrawable(R.drawable.custom_button_true));
                    blockButton(finalView);
                } else {
                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(pattern, -1);
                    }
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonTextArrayList.get(2).isAnswerTrue()) {
                    b3.setBackground(res.getDrawable(R.drawable.custom_button_true));
                    blockButton(finalView);

                } else {
                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(pattern, -1);
                    }
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonTextArrayList.get(3).isAnswerTrue()) {
                    b4.setBackground(res.getDrawable(R.drawable.custom_button_true));
                    blockButton(finalView);

                } else {
                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(pattern, -1);
                    }
                }
            }
        });


        return view;

    }
    void blockButton(View view){
        view.findViewById(R.id.buttonChoiceFirst).setEnabled(false);
        view.findViewById(R.id.buttonChoiceSecond).setEnabled(false);
        view.findViewById(R.id.buttonChoiceThird).setEnabled(false);
        view.findViewById(R.id.buttonChoiceFourth).setEnabled(false);
    }

}
