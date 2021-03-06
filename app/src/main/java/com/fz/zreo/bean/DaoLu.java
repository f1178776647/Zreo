package com.fz.zreo.bean;

import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Zero on 2017/5/20.
 */
@DatabaseTable(tableName = DaoLu.TABLE_NAME)
public class DaoLu {
    public static final String TABLE_NAME="_daolu";
    /**
     * ERRMSG : 成功
     * Balance : 3
     * RESULT : S
     */

    private String ERRMSG;
    private int Balance;
    private String RESULT;

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int Balance) {
        this.Balance = Balance;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }
}
