package com.example.tourdeiapplication.ui.home;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;



    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("TourDei");


    }

    public LiveData<String> getText() {
        return mText;
    }



}