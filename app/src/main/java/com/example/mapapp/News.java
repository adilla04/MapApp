
package com.example.mapapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("hz_rpt")
    @Expose
    public String hzRpt;
    @SerializedName("hz_date")
    @Expose
    public String hzDate;
    @SerializedName("hz_time")
    @Expose
    public String hzTime;
    @SerializedName("hz_location")
    @Expose
    public String hzLocation;
    @SerializedName("hz_type")
    @Expose
    public String hzType;
    @SerializedName("hz_desc")
    @Expose
    public String hzDesc;
    @SerializedName("hz_ltd")
    @Expose
    public String hzLtd;
    @SerializedName("hz_lng")
    @Expose
    public String hzLng;



    /**
     *
     * @param hzLocation
     * @param hzRpt
     * @param hzType
     * @param hzType
     * @param  hzDesc
     * @param  hzTime
     *  @param hzDate
     *   @param hzLtd
     *   @param hzLng
     *
     */
    public News(String hzLocation, String hzRpt, String hzType, String hzDesc, String hzTime, String hzDate, String hzLtd, String hzLng) {
        super();
        this.hzLocation = hzLocation;
        this.hzRpt = hzRpt;
        this.hzType = hzType;
        this.hzDesc = hzDesc;
        this.hzTime = hzTime;
        this.hzDate = hzDate;
        this.hzLtd = hzLtd;
        this.hzLng = hzLng;

    }

    public String getHzLocation() {

        return hzLocation;
    }

    public void setHzLocation(String hzLocation) {

        this.hzLocation = hzLocation;
    }

    public String getHzRpt() {

        return hzRpt;
    }

    public void setHzRpt(String hzRpt) {

        this.hzRpt = hzRpt;
    }

    public String getHzType() {

        return hzType;
    }

    public void setHzType(String hzType) {

        this.hzType = hzType;
    }

    public String getHzDesc() {

        return hzDesc;
    }

    public void setHzDesc(String hzDesc) {

        this.hzDesc = hzDesc;
    }

    public String getHzTime() {

        return hzTime;
    }

    public void setHzTime(String hzTime) {

        this.hzTime = hzTime;
    }

    public String getHzDate() {

        return hzDate;
    }

    public void setHzDate(String hzDate) {

        this.hzDate = hzDate;
    }

    public String getHzLtd() {

        return hzLtd;
    }

    public void setHzLtd(String hzLtd) {

        this.hzLtd = hzLtd;
    }

    public String getHzLng() {

        return hzLng;
    }

    public void setHzLng(String hzLng) {

        this.hzLng = hzLng;
    }



}