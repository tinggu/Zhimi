package com.cyou.ui.wheelView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.cyou.ui.R;
import com.cyou.ui.wheelView.wheel.widget.adapters.AbstractWheelTextAdapter;
import com.cyou.ui.wheelView.wheel.widget.views.OnWheelChangedListener;
import com.cyou.ui.wheelView.wheel.widget.views.OnWheelScrollListener;
import com.cyou.ui.wheelView.wheel.widget.views.WheelView;

import java.util.ArrayList;

/**
 * 更改封面对话框
 * 
 * @author ywl
 *
 */
public class ChangeSexDialog extends Dialog implements View.OnClickListener {

	private WheelView wvSex;

	private Context context;
	private OnSexCListener onSexCListener;
	private ArrayList<String> datas = new ArrayList<>();
	private TextView btnSure;
	private TextView btnCancel;
	private SexTextAdapter sexTextAdapter;
	private String strSex = "女生";

	private int maxsize = 24;
	private int minsize = 14;

	public ChangeSexDialog(Context context) {
		super(context, R.style.ShareDialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_myinfo_changesex);
		datas.add(context.getString(R.string.girl));
		datas.add(context.getString(R.string.boy));
		wvSex = (WheelView) findViewById(R.id.wv_sex);
		btnCancel = (TextView)findViewById(R.id.btn_myinfo_cancel);
		btnSure = (TextView)findViewById(R.id.btn_myinfo_sure);
		btnCancel.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		sexTextAdapter = new SexTextAdapter(context, datas, 0, maxsize, minsize);
		wvSex.setVisibleItems(2);
		wvSex.setViewAdapter(sexTextAdapter);
		wvSex.setCurrentItem(0);
		wvSex.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) sexTextAdapter.getItemText(wheel.getCurrentItem());
				strSex = currentText;
				setTextviewSize(currentText, sexTextAdapter);
			}
		});

		wvSex.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) sexTextAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, sexTextAdapter);
			}
		});

	}


	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, SexTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(24);
			} else {
				textvew.setTextSize(14);
			}
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnSure) {
			if (onSexCListener != null) {
				onSexCListener.onClick(strSex);
			}
		} else if (v == btnCancel) {

		} else {
			dismiss();
		}
		dismiss();
	}

	/**
	 * 回调接口
	 * 
	 * @author Administrator
	 *
	 */
	public interface OnSexCListener {
		public void onClick(String sex);
	}


	private class SexTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected SexTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
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
	}

	public void setOnSexCListener(OnSexCListener onSexCListener){
		this.onSexCListener = onSexCListener;
	}

}