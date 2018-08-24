package com.example.androidm3.db;

import android.provider.BaseColumns;

public class Data implements BaseColumns {

    /**
     * 数据库信息
     */
    public static final String SQLITE_NAME   = "sqlitedong.db";

    public static final int    SQLITE_VERSON = 1;

    /**
     * 信息表，及其字段
     */
    public static final String TABLE_NAME1 = "ResultData";

    public static final String COLUMN_NUM = "num";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_TYPE = "type";
    public static final String CLOUMN_PLACE = "place";
    public static final String CLOUMN_RESULT = "result";
    public static final String CLOUMN_PHOTO = "photo";

    public static final String TABLE_NAME2 = "ManagementData";

    public static final String COLUMN_RELEASE_TIME = "release_time";
    public static final String COLUMN_MODEL = "model";
    public static final String CLOUMN_VERSION = "version";
    public static final String CLOUMN_DESCRIBE = "describe";
    public static final String CLOUMN_CONDITION = "condition";

    //    public static final String COLUMN_DATE="date";

    /**
     * 时间字段的格式
     */
    public static final String DATE_FORMAT = "YYYY-MM-DD HH:MM:SS";

    /**
     * 时间字段的降序，采用date函数比较
     */
    public static final String ORDER_BY = "date(" + COLUMN_TIME + ") desc";
}
