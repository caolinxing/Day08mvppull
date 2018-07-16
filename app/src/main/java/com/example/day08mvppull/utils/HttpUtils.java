package com.example.day08mvppull.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    private static HttpUtils httpUtils = new HttpUtils();

    public static HttpUtils getInstance() {
        if(httpUtils!=null){
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    private HttpUtils() {
    }

    public void getUrl(String api,HttpGetData httpGetData){
        new HttpAsyncTask(httpGetData).execute(api);
    }
    class HttpAsyncTask extends AsyncTask<String,Void,String>{

        private HttpGetData httpGetData;
        public HttpAsyncTask(HttpGetData httpGetData) {
            this.httpGetData = httpGetData;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                String data = SwapInputStreamToString.SwapInputStreamToString(connection.getInputStream());
                httpGetData.onSuccess(data);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
     }


    public interface HttpGetData{
        void onSuccess(String data);
    }
}
