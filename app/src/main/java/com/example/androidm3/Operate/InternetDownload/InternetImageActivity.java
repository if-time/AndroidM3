package com.example.androidm3.Operate.InternetDownload;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.os.Handler;

import com.example.androidm3.R;

import java.io.InputStream;
import java.util.Random;

public class InternetImageActivity extends Activity {
    private ImageView ivInternet;
    private Handler   handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoshow);

        ivInternet = (ImageView) findViewById(R.id.photo_result);

        // 定义一个handler，用于接收消息
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bitmap bmp = null;
                // 通过消息码确定使用什么方式传递图片信息
                if (msg.what == 0) {                                        //使用obj传递数据
                    bmp = (Bitmap) msg.obj;
                } else {                                                    //使用Bundle传递数据
                    Bundle ble = msg.getData();
                    bmp = (Bitmap) ble.get("bmp");
                }
                // 设置图片到ImageView中
                ivInternet.setImageBitmap(bmp);
            }
        };
    }

    public void onClick(View view) {
        ivInternet.setImageBitmap(null);
        //定义一个线程类
        new Thread() {
            @Override
            public void run() {
                try {
                    //获取网络图片
                    InputStream inputStream = HttpUtils.getImageViewInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Message msg = new Message();
                    Random rd = new Random();
                    int ird = rd.nextInt(10);
                    //通过一个随机数，随机选择通过什么方式传递图片信息到消息中
                    if (ird / 2 == 0) {
                        msg.what = 0;
                        msg.obj = bitmap;
                    } else {
                        Bundle bun = new Bundle();
                        bun.putParcelable("bmp", bitmap);
                        msg.what = 1;
                        msg.setData(bun);
                    }
                    //发送消息
                    handler.sendMessage(msg);
                } catch (Exception e) {

                }
            }
        }.start();
    }
}