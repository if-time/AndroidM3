package com.example.androidm3.Operate.InternetDownload;

import com.example.androidm3.Operate.ResultonClickHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
//    private final static String URL_PATH = "http://ww4.sinaimg.cn/bmiddle/9e58dccejw1e6xow22oc6j20c80gyaav.jpg";
    private  final static String URL_PATH = ResultonClickHandler.URL_PATH;
    public HttpUtils() {
    }

    public static InputStream getImageViewInputStream() throws IOException {
        InputStream inputStream = null;
        URL url = new URL(URL_PATH);
        if (url != null) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url
                    .openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            int response_code = httpURLConnection.getResponseCode();
            if (response_code == 200) {
                inputStream = httpURLConnection.getInputStream();
            }
        }
        return inputStream;
    }
}