package com.fz.zreo.db;

import android.content.Context;

import com.fz.zreo.bean.DaoLu;
import com.fz.zreo.utils.BaseDao;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by Zero on 2017/5/24.
 */

public class DaoluDao extends BaseDao {
    public DaoluDao(Context context) {
        super(context);
    }

    @Override
    public Dao getDao() {
        try {
            return getHelper().getDao(DaoLu.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
