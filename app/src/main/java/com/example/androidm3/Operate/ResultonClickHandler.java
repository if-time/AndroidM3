package com.example.androidm3.Operate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidm3.Operate.InternetDownload.InternetImageActivity;
import com.example.androidm3.R;
import com.example.androidm3.db.Data;
import com.example.androidm3.db.DataHelper;

/*
 * 显示照片
 * */
public class ResultonClickHandler {

    public static String URL_PATH;

    private ImageView photo_result;

    public void onClickLookUp(View view) {

        Toast.makeText(view.getContext(), "Hello Photo" , Toast.LENGTH_SHORT).show();

        DataHelper dbHelper = new DataHelper(view.getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        URL_PATH = queryAction();


        /*
        * 自定义布局
        * setView()只会覆盖AlertDialog的Title与Button之间的那部分，而setContentView()则会覆盖全部，
        * setContentView()必须放在show()的后面
        * */
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        View v = inflater.inflate(R.layout.photoshow, null);

        /*
         * 获取照片
         * */
        new InternetImageActivity().onClick(v);

        final Dialog dialog = builder.create();
        dialog.show();

        dialog.getWindow().setContentView(v);       //自定义布局应该在这里添加，要在dialog.show()的后面
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置

    }

    private String queryAction() {

        return URL_PATH;
    }
}
