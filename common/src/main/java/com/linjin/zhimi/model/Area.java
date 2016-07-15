package com.linjin.zhimi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Area {

    @Expose
    @SerializedName("areaname")
    public String areaname;

    @Expose
    @SerializedName("areas")
    public List<Province> provinces;

    @Override
    public String toString() {
        return "Area{" +
                "areaname='" + areaname + '\'' +
                '}';
    }

    public class Province {
        @Expose
        @SerializedName("name")
        public String name;

        @Expose
        @SerializedName("cities")
        public List<String> citys;

        public String areaname;

        public List<String> getCitys() {
            return citys;
        }

        @Override
        public String toString() {
            return "Province{" +
                    "name='" + name + '\'' +
                    ", areaname='" + areaname + '\'' +
                    '}';
        }
    }

}
