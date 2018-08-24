package com.example.androidm3.Operate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.androidm3.db.Data;
import com.example.androidm3.db.DataHelper;

public class SetPhoto {

    public static String Lookup(View view, String num) {

        DataHelper dbHelper = new DataHelper(view.getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select photo from ResultData where num=?", new String[]{num});

        if (cursor.moveToFirst()) {
            String photo = cursor.getString(cursor.getColumnIndex(Data.CLOUMN_PHOTO));  //名称
            return photo;

        }
        cursor.close();
        return null;
    }
}
