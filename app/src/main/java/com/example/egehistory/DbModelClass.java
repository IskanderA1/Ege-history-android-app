package com.example.egehistory;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class DbModelClass {
    int _id;
    String name;
    String trueAnswer;
    String falseAnswer;
    String description;
    String image;


    public DbModelClass(int _id, String name, String trueAnswer, String falseAnswer, String description, String image) {
        this._id = _id;
        this.name = name;
        this.trueAnswer = trueAnswer;
        this.falseAnswer = falseAnswer;
        this.description = description;
        this.image = image;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public String getFinishDate() {
        return falseAnswer;
    }

    public void setFinishDate(String finishDate) {
        this.falseAnswer = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getFalseAnswer(int i){
        String[] firstAnswerArr = falseAnswer.trim().split("\\|");
        Log.d("GetFalseANSWER", Arrays.toString(firstAnswerArr));

        return firstAnswerArr[i];
    }


}
