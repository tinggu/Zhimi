package com.cyou.common.base.view.layout;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cyou.common.base.ListPresenter;
import com.cyou.common.base.view.viewholder.BaseViewHolder;
import com.cyou.common.base.view.viewholder.CommonFooterVH;
import com.cyou.common.entity.RESTResult;
import com.cyou.common.rx.RxManager;
import com.linjin.zhimi.C;
import com.linjin.zhimi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

/**
 * Description:  Create by wangjia_bi on 2016/7/15 21:10
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/7/15 21:10
 */
public class TRecyclerView<M extends RESTResult> extends LinearLayout {
    private static final String TAG = "TRecyclerView";
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @BindView(R.id.ll_emptyview)
    LinearLayout ll_emptyview;

    private LinearLayoutManager mLayoutManager;
    private Context context;
    private CoreAdapter<M> mCommAdapter = new CoreAdapter<>(true);
    private int begin = 0;
    private boolean isRefreshable = true, isHasHeadView = false, isEmpty = false;
    private ListPresenter model;
    public RxManager mRxManager = new RxManager();
    private Map<String, String> param = new HashMap<>();

    public TRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public TRecyclerView(Context context, AttributeSet att) {
        super(context, att);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mRxManager.clear();
    }

    public void init(Context context) {
        this.context = context;
        View layout = LayoutInflater.from(context).inflate(
                R.layout.common_layout_list_recyclerview, null);

        layout.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        addView(layout);
        ButterKnife.bind(this, layout);
        initView(context);
    }

    private void initView(Context context) {
        swiperefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        swiperefresh.setEnabled(isRefreshable);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reFetch();
            }
        });
        recyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(mCommAdapter);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            protected int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerview.getAdapter() != null
                        && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == recyclerview.getAdapter()
                        .getItemCount() && mCommAdapter.isHasMore)
                    fetch();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int arg0, int arg1) {
                super.onScrolled(recyclerView, arg0, arg1);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
        mRxManager.on(C.EVENT_DEL_ITEM, new Action1<Object>() {

            @Override
            public void call(Object arg0) {
                mCommAdapter.removeItem((Integer) arg0);
            }

        });

        mRxManager.on(C.EVENT_UPDATE_ITEM, new Action1<Object>() {
            @Override
            public void call(Object arg0) {
                mCommAdapter.upDateItem(((UpDateData) arg0).i, ((UpDateData) arg0).oj);
            }

        });
//        mRxManager.on(C.EVENT_UPDATE_ITEM, (arg0) -> mCommAdapter.upDateItem(((UpDateData) arg0).i, ((UpDateData) arg0).oj));

        ll_emptyview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reFetch();
            }
        });
    }

    public CoreAdapter getAdapter() {
        return mCommAdapter;
    }

    public void setRefreshing(boolean i) {
        swiperefresh.setRefreshing(i);
    }

    public TRecyclerView setIsRefreshable(boolean i) {
        isRefreshable = i;
        swiperefresh.setEnabled(i);
        return this;
    }

    public TRecyclerView setHeadView(Class<? extends BaseViewHolder> cla, int headType, Object headData) {
        if (cla == null || headType == 0) {
            isHasHeadView = false;
            this.mCommAdapter.setHeadViewType(0, null, null);
        } else
            try {
                if (headData == null) {
                    headData = ((Activity) context).getIntent().getSerializableExtra(C.HEAD_DATA);
                }
                if (headType == 0) {
                    headType = ((cla.getConstructor(View.class).newInstance(new View(context)))).getType();
                }
                this.mCommAdapter.setHeadViewType(headType, cla, headData);
                isHasHeadView = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return this;
    }

    public TRecyclerView setFooterView(Class<? extends BaseViewHolder> cla) {
        this.begin = 0;
        try {
            int mFooterViewType = ((BaseViewHolder) (cla.getConstructor(View.class)
                    .newInstance(new View(context)))).getType();
            this.mCommAdapter.setFooterViewType(mFooterViewType, cla);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void setEmpty() {
//        if (!isHasHeadView && !isEmpty) {
        if (!isEmpty) {
            isEmpty = true;
            ll_emptyview.setVisibility(View.VISIBLE);
            swiperefresh.setVisibility(View.GONE);
        }
    }

    public TRecyclerView setView(Class<? extends BaseViewHolder<M>> cla, ListPresenter listPresenter) {
        Log.i(TAG, "setView: ");
        try {
            BaseViewHolder mIVH = ((BaseViewHolder) (cla.getConstructor(View.class)
                    .newInstance(new View(context))));
            int mType = mIVH.getType();
//            this.model = ((Class<M>) ((ParameterizedType) (cla
//                    .getGenericSuperclass())).getActualTypeArguments()[0])
//                    .newInstance();// 根据类的泛型类型获得model的实例 
            this.model = listPresenter;
            this.mCommAdapter.setViewType(mType, cla);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

//    public TRecyclerView setParam(String key, String value) {
//        this.param.put(key, value);
//        return this;
//    }

    public TRecyclerView setData(List<M> datas) {
        if (isEmpty) {
            ll_emptyview.setVisibility(View.GONE);
            swiperefresh.setVisibility(View.VISIBLE);
        }
        mCommAdapter.setBeans(datas, 1);
        return this;
    }

    public void reFetch() {
        this.begin = 0;
        swiperefresh.setRefreshing(true);
        fetch();
    }

    public void fetch() {
        Log.i(TAG, "fetch: ");
        begin++;
        if (isEmpty) {
            ll_emptyview.setVisibility(View.GONE);
            swiperefresh.setVisibility(View.VISIBLE);
        }
        if (model == null) {
            Log.e("model", "null");
            return;
        }
//        model.setParam(param);

        Observable<List<M>> observable = model.getPageAt(begin);
        if (observable == null) {
            setEmpty();
            return;
        }

        mRxManager.add(observable
                .subscribe(
                        new Action1<List<M>>() {
                            @Override
                            public void call(List<M> subjects) {
                                swiperefresh.setRefreshing(false);
                                mCommAdapter.setBeans(subjects, begin);
                                if (begin == 1 && (subjects == null || subjects.size() == 0))
                                    setEmpty();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable e) {
                                e.printStackTrace();
                                setEmpty();
                            }
                        }
                ));
    }


    public class UpDateData {
        public int i;
        public M oj;

        public UpDateData(int i, M oj) {
            this.i = i;
            this.oj = oj;
        }
    }


    public class CoreAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        protected List<T> mItemList = new ArrayList<>();
        public boolean isHasMore = true;

        public int mViewtype, isHasFooter, isHasHader, mHeadViewType, mFooterViewType;
        public Object mHeadData;
        //footer 默认值
        public Class<? extends BaseViewHolder> mItemViewClass, mHeadViewClass, mFooterViewClass;

        public CoreAdapter(boolean isHasFooterView) {
            if (isHasFooterView)
                setDefaultFooterViewType();
        }

        public void setDefaultFooterViewType() {
            mFooterViewClass = CommonFooterVH.class;
            isHasFooter = 1;
            mFooterViewType = CommonFooterVH.LAYOUT_TYPE;
        }

        public void setViewType(int type, Class<? extends BaseViewHolder> cla) {
            this.isHasMore = true;
            this.mViewtype = type;
            this.mItemList = new ArrayList<>();
            this.mItemViewClass = cla;
            notifyDataSetChanged();
        }

        public void setHeadViewType(int type, Class<? extends BaseViewHolder> cla, Object data) {
            if (cla == null) {
                this.isHasHader = 0;
            } else {
                this.isHasHader = 1;
                this.mHeadViewType = type;
                this.mHeadViewClass = cla;
                this.mHeadData = data;
            }
        }

        public void setHeadViewData(Object data) {
            this.mHeadData = data;
        }

        public void setFooterViewType(int i, Class<? extends BaseViewHolder> cla) {
            this.isHasFooter = 1;
            this.mFooterViewType = i;
            this.mFooterViewClass = cla;
            this.mItemList = new ArrayList<>();
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            Log.i(TAG, "getItemViewType: " + position);
//            if (isHasHader == 1) {
//                if (position == 0) {
//                    return mHeadViewType;
//                } else if (position + 1 == getItemCount()) {
//                    return mFooterViewType;
//                } else {
//                    return mViewtype;
//                }
//            } else if (position + 1 == getItemCount()) {
//                return mFooterViewType;
//            } else {
//                return mViewtype;
//            }

            return isHasHader == 1 ?
                    (position == 0 ? mHeadViewType : (position + 1 == getItemCount() ? mFooterViewType : mViewtype))
                    : (position + 1 == getItemCount() ? mFooterViewType : mViewtype);
        }

        @Override
        public int getItemCount() {
            return mItemList.size() + isHasFooter + isHasHader;
        }

        public void setBeans(List<T> datas, int begin) {
            if (datas == null) datas = new ArrayList<>();
            this.isHasMore = datas.size() >= C.PAGE_COUNT;
            if (begin > 1) {
                this.mItemList.addAll(datas);
            } else {
                this.mItemList = datas;
            }
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            try {
                Log.i(TAG, "onCreateViewHolder: " + viewType);
                if (viewType == mHeadViewType) {
                    return mFooterViewClass.getConstructor(View.class).newInstance(
                            LayoutInflater.from(parent.getContext()).inflate(
                                    mHeadViewType, parent, false));
                } else if (viewType == mViewtype) {
                    return mItemViewClass.getConstructor(View.class).newInstance(
                            LayoutInflater.from(parent.getContext()).inflate(
                                    mViewtype, parent, false));
                } else if (viewType == mFooterViewType) {
                    return mFooterViewClass.getConstructor(View.class).newInstance(
                            LayoutInflater.from(parent.getContext()).inflate(
                                    mFooterViewType, parent, false));
                } else
                    return null;

//                boolean isFoot = viewType == mFooterViewType;
//                return (viewType == mHeadViewType ? mHeadViewClass
//                        .getConstructor(View.class).newInstance(
//                                LayoutInflater.from(parent.getContext()).inflate(
//                                        mHeadViewType, parent, false))
//                        : (RecyclerView.ViewHolder) (isFoot ? mFooterViewClass : mItemViewClass)
//                        .getConstructor(View.class).newInstance(
//                                LayoutInflater.from(parent.getContext())
//                                        .inflate(isFoot ? mFooterViewType : mViewtype, parent,
//                                                false)));
            } catch (Exception e) {
                Log.d("ViewHolderException", "onCreateViewHolder十有八九是xml写错了,哈哈");
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Log.i(TAG, "onBindViewHolder position : " + position);
            BaseViewHolder BaseViewHolder = (BaseViewHolder) holder;
            int type = getItemViewType(position);
            if (type == mHeadViewType) {
                BaseViewHolder.onBindViewHolder(holder.itemView, mHeadData);
            } else if (type == mFooterViewType) {
                BaseViewHolder.onBindViewHolder(holder.itemView, isHasMore ? new Object() : null);
            } else if (type == mViewtype) {
                BaseViewHolder.onBindViewHolder(holder.itemView, mItemList.get(position - isHasHader));
            }
            
//            ((BaseViewHolder) holder).onBindViewHolder(holder.itemView,
//                    position + 1 == getItemCount() ? (isHasMore ? new Object()
//                            : null) : isHasHader == 1 && position == 0 ? mHeadData
//                            : mItemList.get(position - isHasHader));
        }

        public void removeItem(int position) {
            mItemList.remove(position);
            notifyItemRemoved(position);
        }

        public void upDateItem(int position, T item) {
            mItemList.remove(position);
            mItemList.add(position, item);
            notifyItemChanged(position);
        }


    }
}