package com.example.androidm3.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bigkoo.pickerview.view.TimePickerView;
import com.example.androidm3.Operate.ResultonClickHandler;
import com.example.androidm3.TitleView;
import com.example.androidm3.adapter.ResultAdapter;
import com.example.androidm3.databinding.ResultItemBinding;
import com.example.androidm3.db.DBSqliteOpenHelper;
import com.example.androidm3.db.Data;
import com.example.androidm3.db.DataHelper;
import com.example.androidm3.orm.trecogntionresult.Result;

import com.example.androidm3.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecognitionResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TitleView titleView;
    private RecyclerView recyclerView;
    private ResultItemBinding binding;
    private Result result;
    List<Result> resultData = new ArrayList<>();
    ResultAdapter mResultAdapter;

    private ImageView time_choose1;
    private ImageView time_choose2;

    private TimePickerView time_c1, time_c2;

    private TextView time_text1, time_text2;
    private DataHelper dbHelper;
    private Button createDatabase;
    private Button addData;

    private EditText search_text;
    private Button   searchBtn;

    SQLiteDatabase database = null;
    Cursor         cursor   = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recognition_result);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recognition_result);


        /*
        * 导入数据库
        * */
/*
        DBSqliteOpenHelper sqliteOpenHelper = new DBSqliteOpenHelper(this);
        try {
            sqliteOpenHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/


        for (int i = 0; i < 20; i++) {
            result = new Result();
            result.number.set(1);
            result.time.set("2018-09-02 12:32:43");
            result.type.set("车辆识别");
            result.place.set("济南市舜华路");
            result.result.set("鲁 A88888 打电话");
            resultData.add(result);
        }

        /*
         * 选择时间
         * */

        //等数据加载完毕再初始化并显示Picker,以免还未加载完数据就显示,造成APP崩溃。

        initCustomTimePicker();

        time_choose1 = findViewById(R.id.time_choose1);
        time_choose2 = findViewById(R.id.time_choose2);
        time_text1 = findViewById(R.id.time_text1);
        time_text2 = findViewById(R.id.time_text2);

        search_text = findViewById(R.id.search_text);
        searchBtn = findViewById(R.id.search_button);

        time_choose1.setOnClickListener(this);
        time_choose2.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

        /*
         * 创建数据库
         * */
        /*dbHelper = new DataHelper(this);
        createDatabase = findViewById(R.id.search_button);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
                }
        });*/

        /*
         *插入数据 1
         * */
       /* addData = findViewById(R.id.search_button);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put(Data.COLUMN_TIME, "2019-09-12 12:22:33");
                values.put(Data.COLUMN_TYPE, "车牌识别");
                values.put(Data.CLOUMN_PLACE, "济南市舜华路");
                values.put(Data.CLOUMN_RESULT, "鲁 A88778 打电话");
                values.put(Data.CLOUMN_PHOTO, "");
                db.insert(Data.TABLE_NAME1, null, values);
                values.clear();

                values.put(Data.COLUMN_RELEASE_TIME, "2018-04-12 15:23:43");
                values.put(Data.COLUMN_TYPE, "车牌识别");
                values.put(Data.CLOUMN_PHOTO, "");
                values.put(Data.CLOUMN_PLACE, "济南市舜华路");
                values.put(Data.CLOUMN_RESULT, "鲁 A88888 打电话");
                db.insert(Data.TABLE_NAME1, null, values);

                values.put(Data.COLUMN_RELEASE_TIME, "2018-09-09 14:23:43");
                values.put(Data.COLUMN_TYPE, "车牌识别");
                values.put(Data.CLOUMN_PHOTO, "");
                values.put(Data.CLOUMN_PLACE, "济南市舜华路");
                values.put(Data.CLOUMN_RESULT, "鲁 A88888");
                db.insert(Data.TABLE_NAME1, null, values);

            }
        });*/

        /*
         *插入数据 2
         * */
        /*addData = findViewById(R.id.search_button);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put(Data.COLUMN_RELEASE_TIME, "2019-09-12 12:22:33");
                values.put(Data.COLUMN_MODEL, "车牌识别");
                values.put(Data.CLOUMN_VERSION, 1.0);
                values.put(Data.CLOUMN_DESCRIBE, "能够识别车身颜色");
                values.put(Data.CLOUMN_CONDITION, 1);
                db.insert(Data.TABLE_NAME2, null, values);
                values.clear();

                values.put(Data.COLUMN_RELEASE_TIME, "2018-04-12 15:23:43");
                values.put(Data.COLUMN_MODEL, "车牌识别");
                values.put(Data.CLOUMN_VERSION, 1.2);
                values.put(Data.CLOUMN_DESCRIBE, "在1.0的基础上,增加大众、丰田车型识别");
                values.put(Data.CLOUMN_CONDITION, 1);
                db.insert(Data.TABLE_NAME2, null, values);

                values.put(Data.COLUMN_RELEASE_TIME, "2018-09-09 14:23:43");
                values.put(Data.COLUMN_MODEL, "车牌识别");
                values.put(Data.CLOUMN_VERSION, 2.0);
                values.put(Data.CLOUMN_DESCRIBE, "在1.2的基础上,增加对法拉利车型的识别");
                values.put(Data.CLOUMN_CONDITION, 1);
                db.insert(Data.TABLE_NAME2, null, values);

                values.put(Data.COLUMN_RELEASE_TIME, "2018-09-09 15:23:43");
                values.put(Data.COLUMN_MODEL, "车牌识别");
                values.put(Data.CLOUMN_VERSION, 2.0);
                values.put(Data.CLOUMN_DESCRIBE, "在1.2的基础上,增加对法拉利车型的识别");
                values.put(Data.CLOUMN_CONDITION, 1);
                db.insert(Data.TABLE_NAME2, null, values);
                    }
                });*/

        /*
        *
        * */
        /*
         * 向数据库请求数据
         * */
        try {
            //            list = new ArrayList<>();
            database = dbHelper.getWritableDatabase();
            //              database = SQLiteDatabase.openDatabase("", null, SQLiteDatabase.OPEN_READWRITE);
            cursor = database.query(Data.TABLE_NAME1, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    result = new Result();
                    result.number.set(1);
                    result.time.set("2018-09-02 12:32:43");
                    result.type.set("车辆识别");
                    result.place.set("济南市舜华路");
                    result.result.set("鲁 A88888 打电话");
                    resultData.add(result);

                    String id = cursor.getString(cursor.getColumnIndex(Data.COLUMN_NUM));
                    String date = cursor.getString(cursor.getColumnIndex(Data.COLUMN_TIME));
                    String model = cursor.getString(cursor.getColumnIndex(Data.COLUMN_MODEL));
                    String version = cursor.getString(cursor.getColumnIndex(Data.CLOUMN_VERSION));
                    String describe = cursor.getString(cursor.getColumnIndex(Data.CLOUMN_DESCRIBE));
                    String condition = cursor.getString(cursor.getColumnIndex(Data.CLOUMN_CONDITION));
                    resultData.add(new re(id, date, model, version, describe, condition));
                }while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {//释放资源
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }


        /*
        * 显示识别结果界面
        * */
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        /*
        * 显示照片
        * */
//        binding.setLookUp(new ResultonClickHandler());

        mResultAdapter = new ResultAdapter(resultData);
        recyclerView.setAdapter(mResultAdapter);

        /*
        * 延迟
        * */
        delay();

        /*
        * 刷新UI
        * */
//        new Thread(new ActivityThread()).start();


        /**
         * 自制标题栏
         * 使用方法，得到view——可以设置中间显示的字和颜色
         */
        titleView = (TitleView) findViewById(R.id.title);
        titleView.bind(RecognitionResultActivity.this);

        /*
        * 将系统自带的标题栏隐藏
        * */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void delay() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resultData.clear();
                for (int i = 0; i < 20; i++) {
                    result = new Result();
                    result.number.set(2 + i);
                    result.time.set("2018-10-02 12:32:43");
                    result.type.set("车辆识别");
                    result.place.set("济南市舜华路");
                    result.result.set("鲁 A86666 打电话");
                    resultData.add(result);
                }
                mResultAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    /*class ActivityThread implements Runnable {

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                //使用postInvalidate可以直接在线程中更新界面

            }
        }
    }*/
}
