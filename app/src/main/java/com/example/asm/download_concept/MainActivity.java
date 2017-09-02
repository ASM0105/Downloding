package com.example.asm.download_concept;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends AppCompatActivity {
   public class DownloadTask extends AsyncTask<String, Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            String result="";
            URL url;
            HttpURLConnection urlConnection=null;


            try{

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
            //    int data = reader.read();

                BufferedReader data1=new BufferedReader(reader);

                String inputLine = null;
                while ((inputLine = data1.readLine()) != null) {
                      System.out.println("hii baby "+inputLine);
                    result += inputLine;
                }

            }
            catch(Exception e){
                e.printStackTrace();
            }
            Log.i("URL content", result);
            return result;
        }



   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String result =null;

        DownloadTask task = new DownloadTask();
        try {
            task.execute("https://www.pastemagazine.com/articles/2017/02/the-top-100-pokemon-of-all-time.html?a=1");

        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
