package io.terno.lasallesocial.base;

import android.support.v7.app.AppCompatActivity;

import io.terno.lasallesocial.MyApplication;


public class BaseActivity extends AppCompatActivity {

    public MyApplication getApp(){
        return (MyApplication)this.getApplication();
    }
}
