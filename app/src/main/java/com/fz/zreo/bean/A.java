package com.fz.zreo.bean;

import java.util.List;

/**
 * Created by Zero on 2017/5/20.
 */

public class A {


    /**
     * RESULT : S
     * ERRMSG : 查 询 成 功
     * ROWS_DETAIL : [{"Addr":"etcout","Cost":5,"CarId":0,"Time":"2017-04-20 03:29:06"},{"Addr":"etcout","Cost":5,"CarId":0,"Time":"2017-04-20 03:31:56"},{"Addr":"etcout","Cost":5,"CarId":0,"Time":"2017-04-20 05:05:26"},{"Addr":"parkout","Cost":5,"CarId":1,"Time":"2017-04-22 03:48:14"},{"Addr":"etcout","Cost":5,"CarId":0,"Time":"2017-04-22 03:48:25"},{"Addr":"parkout","Cost":5,"CarId":1,"Time":"2017-04-22 03:40:45"},{"Addr":"etcout","Cost":5,"CarId":0,"Time":"2017-04-2203:40:57"},{"Addr":"parkout","Cost":5,"CarId":1,"Time":"2017-04-22 03:42:37"},{"Addr":"etcout","Cost":5,"CarId":0,"Time":"2017-04-22 03:42:49"},{"Addr":"parkout","Cost":5,"CarId":1,"Time":"2017-04-22 03:44:30"},{"Addr":"etcout","Cost":5,"CarId":0,"Time":"2017-04-22 03:44:41"},{"Addr":"parkout","Cost":5,"CarId":1,"Time":"2017-04-22 03:46:22"},{"Addr":"etcout","Cost":5,"CarId":0,"Time":"2017-04-22 03:46:33"},{"Addr":"parkout","Cost":5,"CarId":1,"Time":"2017-04-22 03:48:14"}]
     */

    private String RESULT;
    private String ERRMSG;
    private List<ROWSDETAILBean> ROWS_DETAIL;

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * Addr : etcout
         * Cost : 5
         * CarId : 0
         * Time : 2017-04-20 03:29:06
         */

        private String Addr;
        private int Cost;
        private int CarId;
        private String Time;

        public String getAddr() {
            return Addr;
        }

        public void setAddr(String Addr) {
            this.Addr = Addr;
        }

        public int getCost() {
            return Cost;
        }

        public void setCost(int Cost) {
            this.Cost = Cost;
        }

        public int getCarId() {
            return CarId;
        }

        public void setCarId(int CarId) {
            this.CarId = CarId;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }
    }
}
