package com.example.bbc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class StartPage extends AppCompatActivity {

    AlertDialog.Builder builder;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_start_page);
            ImageView imageView = findViewById(R.id.imageView);
            ImageView imageView2 = findViewById(R.id.imageView2);
            ImageView alert2 = findViewById(R.id.alert2);
            builder = new AlertDialog.Builder(this);

                View.OnClickListener listener = new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.imageView:
                                Intent intent = new Intent(StartPage.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),"Welcome!",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.imageView2:
                                Intent intent2 = new Intent(StartPage.this, MainActivity.class);
                                startActivity(intent2);
                                Toast.makeText(getApplicationContext(),"Welcome!",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.alert2:
                                builder.setMessage(R.string.Dialog_message) .setTitle(R.string.Dialog_title);
                                builder.setMessage("Click on approriate icon.")
                                        .setCancelable(true)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert = builder.create();
                                alert.setTitle("Instructions:");
                                alert.show();
                                break;
                        }
                    }
                };
        imageView.setOnClickListener(listener);
       imageView2.setOnClickListener(listener);
       alert2.setOnClickListener(listener);
            }}
