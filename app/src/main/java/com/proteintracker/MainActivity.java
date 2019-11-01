package com.proteintracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("abc","test");
        super.onSaveInstanceState(outState);
    }

    private View.OnClickListener proteinButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ProteinActivity.class);
            startActivity(intent);
        }
    };


        private View.OnClickListener tableButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TableActivity.class);
                startActivity(intent);
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button proteinBtn = (Button) findViewById(R.id.proteinButton);
        proteinBtn.setOnClickListener(proteinButtonListener);



        Button tableBtn = (Button) findViewById(R.id.tableButton);
        tableBtn.setOnClickListener(tableButtonListener);


        if (savedInstanceState != null) {
            Log.d("Protein Tracker", savedInstanceState.getString("abc"));

        }


        TextView textView = (TextView) findViewById(R.id.mainActivityTextView);
        textView.setText(R.string.test_untuk_update_view);

        Button myBtn = (Button) findViewById(R.id.button1);
        Button btnFragment = (Button) findViewById(R.id.btnFragment);
        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText myEditText = (EditText) findViewById(R.id.editText1);
                Log.d("Proteintracker", myEditText.getText().toString());
            }
        });

        View.OnClickListener FragmentButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main3FragmentActivity.class);
                startActivity(intent);
            }
        };
        btnFragment.setOnClickListener(FragmentButtonListener);

        Button helpBtn = (Button)findViewById(R.id.helpButton);
            View.OnClickListener helpButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                Bundle b = new Bundle();
                EditText myEditText = (EditText)findViewById(R.id.editText1);
                b.putString("helpString",myEditText.getText().toString());
                intent.putExtras(b);
                startActivity(intent);
            }
        };
            helpBtn.setOnClickListener(helpButtonListener);
    }

}