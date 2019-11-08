package com.proteintracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProteinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protein);

        Button customButton = (Button) findViewById(R.id.btnCustomDialog);
        customButton.setOnClickListener(customButtonListener);

        Button resetButton = (Button) findViewById(R.id.btnReset);
        resetButton.setOnClickListener(resetButtonListener);


        Button settingsBtn = (Button) findViewById(R.id.btnSettings);
        settingsBtn.setOnClickListener(myBtnSettingClick);

        SharedPreferences prefs = ProteinActivity.this.getSharedPreferences("prefs_file", MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin", null);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(myBtnLoginClick);

        if
        (statusLogin != null) {
            btnLogin.setText("Logout");
        } else {
            btnLogin.setText("Login");
        }
    }

    private View.OnClickListener myBtnSettingClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProteinActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener myBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs =
                    ProteinActivity.this.getSharedPreferences("prefs_file", MODE_PRIVATE);

            String statusLogin = prefs.getString("isLogin", null);
            SharedPreferences.Editor edit = prefs.edit();
            Button btnLogin = (Button) findViewById(R.id.btnLogin);
            if (statusLogin != null) {
                edit.putString("isLogin", null);
                btnLogin.setText("Login");
            } else {
                edit.putString("isLogin", "Admin");
                btnLogin.setText("Logout");
            }
            edit.commit();
        }
    };
    private View.OnClickListener customButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Dialog dialog = new Dialog(ProteinActivity.this);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.setTitle("Custom Dialog");

            Button btnDialog = (Button) dialog.findViewById(R.id.btnDissmis);
            btnDialog.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            });

            dialog.show();
        }

    };

    public View.OnClickListener resetButtonListener = new View.OnClickListener() {
        ProgressDialog progressDialog;

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ProteinActivity.this);
            builder.setMessage("Apakah anda yakin untuk mereset nilai protein?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ProteinActivity.this, "Tidak jadi reset",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ProteinActivity.this, "Melakukan RESET !!",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog = new ProgressDialog(ProteinActivity.this);
                            progressDialog.setMessage("Melakukan sesuatu ...");
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                            Thread thread = new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        Thread.sleep(3000);
                                        handler.sendEmptyMessage(0);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                            });
                            thread.start();
                            progressDialog.show();
                        }

                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }


        private Handler handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                progressDialog.dismiss();
            }
        };




    };
}

