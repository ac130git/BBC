package com.example.bbc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    public ProgressBar bar;
    public EditText textView2;
    AlertDialog.Builder builder;
    private NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.login:
                    Intent intent = new Intent(MainActivity.this, StartPage.class);
                    startActivity(intent);
                    break;
                case R.id.BBCHome:
                    String url = "https://www.bbc.com/news";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    break;
                case R.id.RSSfeed:
                    String url2 = "http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml";
                    Intent i2 = new Intent(Intent.ACTION_VIEW);
                    i2.setData(Uri.parse(url2));
                    startActivity(i2);
                    break;
            }
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(myToolbar);
        Button info = findViewById(R.id.info);
        bar = findViewById(R.id.bar);
        textView2 = findViewById(R.id.textView2);
        builder = new AlertDialog.Builder(this);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("Https://www.bbc.com/news");
        Button news = findViewById(R.id.news);
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        NavigationView navigation = (NavigationView) findViewById(R.id.navlayout);
        navigation.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.news:
                Snackbar snackbar = Snackbar.make(news, "News have been updated.", Snackbar.LENGTH_LONG);
                snackbar.show();
                webView.reload();
                webView.scrollTo(0,0);
                bar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                break;
                    case R.id.info:
                        Snackbar snackbar2 = Snackbar.make(info, "Search for news here.", Snackbar.LENGTH_LONG);
                        snackbar2.show();
                        break;
            }
        };
    };
        news.setOnClickListener(listener);
       info.setOnClickListener(listener);
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
            case R.id.drawer:
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
                drawer.openDrawer((Gravity.LEFT));
                bar.setVisibility(View.GONE);
                webView.setVisibility(View.GONE);
                break;
            case R.id.bbc:
                Uri uri = Uri.parse( "http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml" );
                startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
                bar.setVisibility(View.GONE);
                break;
            case R.id.sport:
                Uri uri2 = Uri.parse( "https://www.bbc.com/sport" );
                startActivity( new Intent( Intent.ACTION_VIEW, uri2 ) );
                bar.setVisibility(View.GONE);
                break;
            case R.id.alertDialog:
                builder.setMessage(R.string.Dialog_message) .setTitle(R.string.Dialog_title);
                builder.setMessage("Please slide from left to right in to open navigtion drawer.\n" +
                        "The BBC icon brings you to the RSS feed.\n" +
                        "The question mark provides a snackbar.")
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
        return true;

    }
}



