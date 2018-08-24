package com.example.androidm3.orm.trecogntionresult;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class Result {

    public final ObservableInt number = new ObservableInt();
    public final ObservableField<String> time = new ObservableField<>();
    public final ObservableField<String> type  = new ObservableField<>();
    public final ObservableField<String> place = new ObservableField<>();
    public final ObservableField<String> result  = new ObservableField<>();
    //    public final ObservableBoolean       isStudent = new ObservableBoolean();

    /*public int number;
    public String time;
    public String type;
    public String place;
    public String result;

    public Result() {

    }

    public Result(int number, String time, String type, String place, String result) {
        this.number = number;
        this.time = time;
        this.type = type;
        this.place = place;
        this.result = result;
    }

    public int getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getPlace() {
        return place;
    }

    public String getResult() {
        return result;
    }*/



}
