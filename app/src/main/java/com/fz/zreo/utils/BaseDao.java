package com.fz.zreo.utils;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zero on 2017/5/24.
 */

public abstract class BaseDao<T,ID> {
    private DatabaseHelper helper;

    public BaseDao(Context context) {
        helper = DatabaseHelper.getInstance(context);
    }

    public DatabaseHelper getHelper() {
        return helper;
    }

    public abstract Dao<T, ID> getDao();

    public int add(T t){
        try {
            return getDao().create(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int addList(List<T> list){
        try {
            return getDao().create(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public List<T> getAll(){
        List<T> list = new ArrayList<>();
        try {
            list = getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public T get(ID id){
        try {
            return getDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int update(T t){
        try {
            return getDao().update(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int delete(ID id){
        try {
            return getDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
