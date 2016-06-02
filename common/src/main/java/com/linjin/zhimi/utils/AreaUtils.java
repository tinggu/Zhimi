package com.linjin.zhimi.utils;
 
import com.cyou.quick.QuickApplication;
import com.google.gson.reflect.TypeToken;
import com.linjin.zhimi.model.Area;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/1/4 10:35
 */
public class AreaUtils {

    private static ArrayList<Area.Province> provinces;

    public static List<Area.Province> getProvinces() {
        if (provinces == null || provinces.size() == 0) {
            provinces = new ArrayList<>();
            try {
                InputStream inputStream = QuickApplication.getInstance().getResources().getAssets().open("area");
                InputStreamReader reader = new InputStreamReader(inputStream);
                List<Area> areas = GsonUtils.GSON.fromJson(reader, new TypeToken<List<Area>>() {
                }.getType());
                for (Area area : areas) {
                    for (Area.Province province : area.provinces) {
                        province.areaname = area.areaname;
                        provinces.add(province);
                    }
//                    provinces.addAll(area.provinces);
                }

            } catch (Exception e) {
                LogUtils.i("test", e.getMessage() + " " + e);
                e.printStackTrace();
            }


        }
        LogUtils.d("showAreaPicker area:" + provinces);
        return provinces;
    }

    public static String getAreaByProvinces(String provincesName) {
        if (provinces == null) {
            getProvinces();
        }
        for (Area.Province province : provinces) {
            if (province.name.equals(provincesName)) {
                return province.areaname;
            }
        }
        //没有查找到返回默认值
        return "华北";
    }
}
