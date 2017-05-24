package com.fz.zreo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fz.zreo.bean.DaoLu;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Zero on 2017/5/24.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME = "zero";
    private static final int VERSION = 1;
    private static DatabaseHelper databaseHelper;

    public static DatabaseHelper getInstance(Context mContext) {
        mContext = mContext.getApplicationContext();
        if (databaseHelper == null) {
            synchronized (DatabaseHelper.class) {
                if (databaseHelper == null) {
                    databaseHelper = new DatabaseHelper(mContext);
                }
            }
        }
        return databaseHelper;
    }

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, DaoLu.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
    }
}
