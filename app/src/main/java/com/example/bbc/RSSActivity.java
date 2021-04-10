package com.example.bbc;


import android.os.Bundle;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RSSActivity extends ListActivity
{
    //initiliazing variables
    List title;
    List links;
    List description;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //creating new AsyncTask
        new MyAsyncTask().execute();

        
    }

    class MyAsyncTask extends AsyncTask<Object,Void,ArrayAdapter>
    {
        @Override
        protected ArrayAdapter doInBackground(Object[] params)
        {
            title = new ArrayList();
            links = new ArrayList();

            try
            {
                URL url = new URL("https://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(getInputStream(url), "UTF_8");
                boolean insideItem = false;

                //getting title that will display on screen and link that will have action
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    if (eventType == XmlPullParser.START_TAG)
                    {
                        if (xpp.getName().equalsIgnoreCase("item"))
                        {
                            insideItem = true;
                        }
                        else if (xpp.getName().equalsIgnoreCase("title"))
                        {
                            if (insideItem)
                                title.add(xpp.nextText());
                        }
                        else if (xpp.getName().equalsIgnoreCase("link"))
                        {
                            if (insideItem)
                                links.add(xpp.nextText());
                        }
                    }
                    else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item"))
                    {
                        insideItem=false;
                    }
                    eventType = xpp.next();
                }

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (XmlPullParserException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(ArrayAdapter adapter)
        {
            adapter = new ArrayAdapter(RSSActivity.this, android.R.layout.simple_list_item_1, title);
            setListAdapter(adapter);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        Uri uri = Uri.parse((links.get(position)).toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public InputStream getInputStream(URL url)
    {
        try
        {
            return url.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            return null;
        }
    }
}