package com.mhmmdyzci.javafragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
    }
    public void goToFirst(View view){

        fragmentTransaction = fragmentManager.beginTransaction();
        FirstFragment firstFragment = new FirstFragment();
        fragmentTransaction.replace(R.id.frame_layout, firstFragment).commit();


    }
    public void goToSecond(View view){
        fragmentTransaction = fragmentManager.beginTransaction();
        SecondFragment secondFragment = new SecondFragment();
       // fragmentTransaction.add(R.id.frame_layout, secondFragment).commit();
        //add yerine replace dersek fragmentle üst üste binmez
        fragmentTransaction.replace(R.id.frame_layout, secondFragment).commit();

    }
}