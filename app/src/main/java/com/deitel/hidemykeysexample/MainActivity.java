package com.deitel.hidemykeysexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    //accessing my String api key
    String myStringApiKey = BuildConfig.API_STRING;
    //accessing my int api key
    int myIntApiKey = BuildConfig.API_INT;


    //accessing the api string in api_keys.xml
    String mySecondApiString = getString(R.string.my_api_string);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
