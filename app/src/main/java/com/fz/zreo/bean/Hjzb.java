package com.fz.zreo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zero on 2017/5/18.
 */

public class Hjzb {
    /**
     * RESULT : S
     * ERRMSG : 成功
     * pm2.5 : 8
     * co2 : 5919
     * LightIntensity : 1711
     * humidity : 44
     * temperature : 28
     */

    private String RESULT;
    private String ERRMSG;
    @SerializedName("pm2.5")
    private int _$Pm25184; // FIXME check this code
    private int co2;
    private int LightIntensity;
    private int humidity;
    private int temperature;
    private String tiem;

    public String getTiem() {
        return tiem;
    }

    public void setTiem(String tiem) {
        this.tiem = tiem;
    }

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

    public int get_$Pm25184() {
        return _$Pm25184;
    }

    public void set_$Pm25184(int _$Pm25184) {
        this._$Pm25184 = _$Pm25184;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getLightIntensity() {
        return LightIntensity;
    }

    public void setLightIntensity(int LightIntensity) {
        this.LightIntensity = LightIntensity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * RESULT : S
     * ERRMSG : 成功
     * pm2.5 : 8
     * co2 : 5919
     * LightIntensity : 1711
     * humidity : 44
     * temperature : 28
     *//*

    public String RESULT;
    public String ERRMSG;
    @SerializedName("pm2.5")
    public int _$Pm2533; // FIXME check this code
    public int co2;
    public int LightIntensity;
    public int humidity;
    public int temperature;*/

}
