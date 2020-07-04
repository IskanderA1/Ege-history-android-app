package com.example.egehistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;


public class QuestionAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<DbModelClass> objects;

    public QuestionAdapter(Context context, ArrayList<DbModelClass> dbModelClassArrayList) {
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
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        DbModelClass  question = objects.get(position);
        ((TextView) view.findViewById(R.id.MonarchNameTextView)).setText(question.getName());

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.get().load("file:///android_asset/imageMonarch/"+question.getImage()).into(imageView);
        //Picasso.get().load(new File("assets/imageMonarch"+question.getImage())).into(imageView);
        //((ImageView) view.findViewById(R.id.imageView)).setImageBitmap(question.getImage());
        return view;
    }


}
