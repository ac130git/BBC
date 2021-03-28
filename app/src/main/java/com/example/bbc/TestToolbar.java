package com.example.bbc;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class TestToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(myToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bbc:
                Toast.makeText(getApplicationContext(),"You clicked on item 1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sport:
                Toast.makeText(getApplicationContext(),"You clicked on item 2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.alertDialog:
                Toast.makeText(getApplicationContext(),"You clicked on item 3",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;

    }
}



