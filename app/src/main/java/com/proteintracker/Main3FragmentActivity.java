package com.proteintracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class Main3FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_fragment);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ProteinFragment proteinFragment = ProteinFragment.newInstance("Hai","Para Progmobers");
        ft.replace(R.id.frameMain, proteinFragment);
        ft.commit();
    }

}
