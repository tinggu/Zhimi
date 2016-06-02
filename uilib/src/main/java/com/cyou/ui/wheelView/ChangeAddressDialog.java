package com.cyou.ui.wheelView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.cyou.ui.R;
import com.cyou.ui.wheelView.wheel.widget.adapters.WheelTextAdapter;
import com.cyou.ui.wheelView.wheel.widget.views.OnWheelChangedListener;
import com.cyou.ui.wheelView.wheel.widget.views.OnWheelScrollListener;
import com.cyou.ui.wheelView.wheel.widget.views.WheelView;
import com.linjin.zhimi.model.Area;
import com.linjin.zhimi.utils.AreaUtils;
import com.linjin.zhimi.utils.LogUtils;

import java.util.List;

/**
 * 更改封面对话框
 *
 * @author ywl
 */
public class ChangeAddressDialog extends Dialog implements View.OnClickListener {

    private WheelView wvProvince;
    private WheelView wvCitys;
    private View lyChangeAddress;
    private View lyChangeAddressChild;
    private TextView btnSure;
    private TextView btnCancel;

    private Context context;
    private ProvinceAdapter provinceAdapter;
    private CityAdapter cityAdapter;

    private OnAddressCListener onAddressCListener;

    private List<Area.Province> provinces;

    public ChangeAddressDialog(Context context) {
        super(context, R.style.ShareDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_myinfo_changeaddress);

        wvProvince = (WheelView) findViewById(R.id.wv_address_province);
        wvCitys = (WheelView) findViewById(R.id.wv_address_city);
        lyChangeAddress = findViewById(R.id.ly_myinfo_changeaddress);
        lyChangeAddressChild = findViewById(R.id.ly_myinfo_changeaddress_child);
        btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
        btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);

        lyChangeAddress.setOnClickListener(this);
        lyChangeAddressChild.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        provinces = AreaUtils.getProvinces();
        provinceAdapter = new ProvinceAdapter(context, provinces);
        wvProvince.setVisibleItems(5);
        wvProvince.setViewAdapter(provinceAdapter);

        cityAdapter = new CityAdapter(context, provinceAdapter.getCurrentItem().getCitys());
        wvCitys.setVisibleItems(5);
        wvCitys.setViewAdapter(cityAdapter);

        wvProvince.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
//                strProvince = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
//                strProvince = currentText;
                provinceAdapter.setCurrentIndex(wheel.getCurrentItem());
                provinceAdapter.setTextviewSize(provinceAdapter.getCurrent());
//                setTextviewSize(currentText, provinceAdapter);
                List<String> citys = provinceAdapter.getCurrentItem().citys;
                cityAdapter = new CityAdapter(context, citys);
                wvCitys.setVisibleItems(5);
                wvCitys.setViewAdapter(cityAdapter);
                wvCitys.setCurrentItem(0);
            }
        });

        wvProvince.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                provinceAdapter.setCurrentIndex(wheel.getCurrentItem());
                provinceAdapter.setTextviewSize(provinceAdapter.getCurrent());
            }
        });

        wvCitys.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                cityAdapter.setCurrentIndex(wheel.getCurrentItem());
                cityAdapter.setTextviewSize(cityAdapter.getCurrent());
            }
        });

        wvCitys.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                cityAdapter.setCurrentIndex(wheel.getCurrentItem());
                cityAdapter.setTextviewSize(cityAdapter.getCurrent());
            }
        });

        wvProvince.setCurrentItem(provinceAdapter.getIndex(strProvince));
        wvCitys.setCurrentItem(cityAdapter.getIndex(strCity));
//        provinceAdapter.setCurrent(strProvince);
//        cityAdapter.setCurrent(strCity);
    }

    class ProvinceAdapter extends WheelTextAdapter {

        public List<Area.Province> mProvinces;

        protected ProvinceAdapter(Context context, List<Area.Province> provinces) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, 0);
            setItemTextResource(R.id.tempValue);
            mProvinces = provinces;
        }

        public Area.Province getItem(int index) {
            return mProvinces.get(index);
        }

        @Override
        protected CharSequence getItemText(int index) {
            return mProvinces.get(index).name;
        }

        @Override
        public int getIndex(String title) {
            int size = mProvinces.size();
            for (int i = 0; i < size; i++) {
                if (mProvinces.get(i).name.equals(title)) {
                    return i;
                }
            }
            return 0;
        }

        @Override
        public void setCurrent(String title) {
            setCurrentIndex(getIndex(title));
        }

        @Override
        public String getCurrent() {
            return mProvinces.get(currentIndex).name;
        }

        @Override
        public int getItemsCount() {
            return mProvinces.size();
        }

        public Area.Province getCurrentItem() {
            return mProvinces.get(currentIndex);
        }

    }

    private class CityAdapter extends WheelTextAdapter {
        List<String> list;

        protected CityAdapter(Context context, List<String> list) {

            super(context, R.layout.item_birth_year, NO_RESOURCE, 0);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }

        @Override
        public int getIndex(String title) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).equals(title)) {
                    return i;
                }
            }
            return 0;
        }

        @Override
        public void setCurrent(String title) {
            setCurrentIndex(getIndex(title));
        }

        @Override
        public String getCurrent() {
            return list.get(currentIndex);
        }
    }


    public void setAddresskListener(OnAddressCListener onAddressCListener) {
        this.onAddressCListener = onAddressCListener;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btnSure) {
            if (onAddressCListener != null) {
//                String strProvince = provinceAdapter.getCurrent();
                Area.Province province = provinceAdapter.getCurrentItem();
                String strCity = cityAdapter.getCurrent();
                LogUtils.d(province.toString());
                onAddressCListener.onClick(province.areaname, province.name, strCity);
            }
        } else if (v == btnCancel) {

        } else if (v == lyChangeAddressChild) {
            return;
        } else {
            dismiss();
        }
        dismiss();
    }

    /**
     * 回调接口
     *
     * @author Administrator
     */
    public interface OnAddressCListener {
        void onClick(String area, String province, String city);
    }

    private String strProvince;
    private String strCity;
    private String strArea;

    /**
     * 初始化地点
     *
     * @param province
     * @param city
     */
    public void setAddress(String area, String province, String city) {
        strArea = area;
        strProvince = province;
        strCity = city;
    }

}