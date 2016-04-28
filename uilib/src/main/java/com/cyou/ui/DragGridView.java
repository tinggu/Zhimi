package com.cyou.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

import java.util.ArrayList;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/28
 **/
@SuppressLint("NewApi")
public class DragGridView extends GridView {
    /**
     * DragGridView自动滚动的速度
     */
    private static final int speed = 80;
    /**
     * DragGridView的item长按响应的时间， 默认是1500毫秒，也可以自行设置
     */
    private long dragResponseMS = 1500;
    /**
     * 是否可以拖拽，默认不可以
     */
    private boolean isDrag = false;
    private int mDownX;
    private int mDownY;
    private int moveX;
    private int moveY;
    /**
     * 正在拖拽的position
     */
    private int mDragPosition;
    /**
     * 刚开始拖拽的item对应的View
     */
    private View mStartDragItemView = null;
    /**
     * 用于拖拽的镜像，这里直接用一个ImageView
     */
    private ImageView mDragImageView;
    /**
     * 震动器
     */
    private Vibrator mVibrator;
    private WindowManager mWindowManager;
    /**
     * item镜像的布局参数
     */
    private WindowManager.LayoutParams mWindowLayoutParams;
    /**
     * 我们拖拽的item对应的Bitmap
     */
    private Bitmap mDragBitmap;
    /**
     * 按下的点到所在item的上边缘的距离
     */
    private int mPoint2ItemTop;
    /**
     * 按下的点到所在item的左边缘的距离
     */
    private int mPoint2ItemLeft;
    /**
     * DragGridView距离屏幕顶部的偏移量
     */
    private int mOffset2Top;
    /**
     * DragGridView距离屏幕左边的偏移量
     */
    private int mOffset2Left;
    /**
     * 状态栏的高度
     */
    private int mStatusHeight;
    /**
     * DragGridView自动向下滚动的边界值
     */
    private int mDownScrollBorder;
    /**
     * DragGridView自动向上滚动的边界值
     */
    private int mUpScrollBorder;
    /**
     * item发生变化回调的接口
     */
    private OnChanageListener onChanageListener;

    private Context context;
    private Handler mHandler = new Handler();
    //用来处理是否为长按的Runnable
    private Runnable mLongClickRunnable = new Runnable() {

        @Override
        public void run() {
            isDrag = true; //设置可以拖拽
            mVibrator.vibrate(50); //震动一下
            mStartDragItemView.setVisibility(View.INVISIBLE);//隐藏该item

            //根据我们按下的点显示item镜像
            createDragImage(mDragBitmap, mDownX, mDownY);
        }
    };
    /**
     * 当moveY的值大于向上滚动的边界值，触发GridView自动向上滚动
     * 当moveY的值小于向下滚动的边界值，触犯GridView自动向下滚动
     * 否则不进行滚动
     */
    private Runnable mScrollRunnable = new Runnable() {

        @Override
        public void run() {
            int scrollY;
            if (moveY > mUpScrollBorder) {
                scrollY = -speed;
                mHandler.postDelayed(mScrollRunnable, 25);
            } else if (moveY < mDownScrollBorder) {
                scrollY = speed;
                mHandler.postDelayed(mScrollRunnable, 25);
            } else {
                scrollY = 0;
                mHandler.removeCallbacks(mScrollRunnable);
            }

            //当我们的手指到达GridView向上或者向下滚动的偏移量的时候，可能我们手指没有移动，但是DragGridView在自动的滚动
            //所以我们在这里调用下onSwapItem()方法来交换item
            onSwapItem(moveX, moveY);

            View view = getChildAt(mDragPosition - getFirstVisiblePosition());
            //实现GridView的自动滚动
            smoothScrollToPositionFromTop(mDragPosition, view.getTop() + scrollY);
        }
    };
    private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<FixedViewInfo>();

    public DragGridView(Context context) {
        this(context, null);
        this.context = context;
        initHeaderGridView();
    }

    public DragGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
        initHeaderGridView();
    }

    public DragGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mStatusHeight = getStatusHeight(context); //获取状态栏的高度

    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    private static int getStatusHeight(Context context) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * 设置回调接口
     *
     * @param onChanageListener
     */
    public void setOnChangeListener(OnChanageListener onChanageListener) {
        this.onChanageListener = onChanageListener;
    }

    /**
     * 设置响应拖拽的毫秒数，默认是1000毫秒
     *
     * @param dragResponseMS
     */
    public void setDragResponseMS(long dragResponseMS) {
        this.dragResponseMS = dragResponseMS;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //使用Handler延迟dragResponseMS执行mLongClickRunnable
                mHandler.postDelayed(mLongClickRunnable, dragResponseMS);

                mDownX = (int) ev.getX();
                mDownY = (int) ev.getY();

                //根据按下的X,Y坐标获取所点击item的position
                mDragPosition = pointToPosition(mDownX, mDownY);

                if (mDragPosition == AdapterView.INVALID_POSITION) {
                    return super.dispatchTouchEvent(ev);
                }

                //根据position获取该item所对应的View
                mStartDragItemView = getChildAt(mDragPosition - getFirstVisiblePosition());

                //下面这几个距离大家可以参考我的博客上面的图来理解下
                mPoint2ItemTop = mDownY - mStartDragItemView.getTop();
                mPoint2ItemLeft = mDownX - mStartDragItemView.getLeft();

                mOffset2Top = (int) (ev.getRawY() - mDownY);
                mOffset2Left = (int) (ev.getRawX() - mDownX);

                //获取DragGridView自动向上滚动的偏移量，小于这个值，DragGridView向下滚动
                mDownScrollBorder = getHeight() / 4;
                //获取DragGridView自动向下滚动的偏移量，大于这个值，DragGridView向上滚动
                mUpScrollBorder = getHeight() * 3 / 4;


                //开启mDragItemView绘图缓存
                mStartDragItemView.setDrawingCacheEnabled(true);
                //获取mDragItemView在缓存中的Bitmap对象
                mDragBitmap = Bitmap.createBitmap(mStartDragItemView.getDrawingCache());
                //这一步很关键，释放绘图缓存，避免出现重复的镜像
                mStartDragItemView.destroyDrawingCache();


                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();

                //如果我们在按下的item上面移动，只要不超过item的边界我们就不移除mRunnable
                if (!isTouchInItem(mStartDragItemView, moveX, moveY)) {
                    mHandler.removeCallbacks(mLongClickRunnable);
                }
                break;
            case MotionEvent.ACTION_UP:
                mHandler.removeCallbacks(mLongClickRunnable);
                mHandler.removeCallbacks(mScrollRunnable);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否点击在GridView的item上面
     *
     * @param x
     * @param y
     * @return
     */
    private boolean isTouchInItem(View dragView, int x, int y) {
        if (dragView == null) {
            return false;
        }
        int leftOffset = dragView.getLeft();
        int topOffset = dragView.getTop();
        if (x < leftOffset || x > leftOffset + dragView.getWidth()) {
            return false;
        }

        if (y < topOffset || y > topOffset + dragView.getHeight()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isDrag && mDragImageView != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    moveX = (int) ev.getX();
                    moveY = (int) ev.getY();
                    //拖动item
                    onDragItem(moveX, moveY);
                    break;
                case MotionEvent.ACTION_UP:
                    onStopDrag();
                    isDrag = false;
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 创建拖动的镜像
     *
     * @param bitmap
     * @param downX  按下的点相对父控件的X坐标
     * @param downY  按下的点相对父控件的X坐标
     */
    private void createDragImage(Bitmap bitmap, int downX, int downY) {
        mWindowLayoutParams = new WindowManager.LayoutParams();
        mWindowLayoutParams.format = PixelFormat.TRANSLUCENT; //图片之外的其他地方透明
        mWindowLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mWindowLayoutParams.x = downX - mPoint2ItemLeft + mOffset2Left;
        mWindowLayoutParams.y = downY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
        mWindowLayoutParams.alpha = 0.55f; //透明度
        mWindowLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        mDragImageView = new ImageView(getContext());
        mDragImageView.setImageBitmap(bitmap);
        mWindowManager.addView(mDragImageView, mWindowLayoutParams);
    }

    /**
     * 从界面上面移动拖动镜像
     */
    private void removeDragImage() {
        if (mDragImageView != null) {
            mWindowManager.removeView(mDragImageView);
            mDragImageView = null;
        }
    }

    /**
     * 拖动item，在里面实现了item镜像的位置更新，item的相互交换以及GridView的自行滚动
     */
    private void onDragItem(int moveX, int moveY) {
        mWindowLayoutParams.x = moveX - mPoint2ItemLeft + mOffset2Left;
        mWindowLayoutParams.y = moveY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
        mWindowManager.updateViewLayout(mDragImageView, mWindowLayoutParams); //更新镜像的位置
        onSwapItem(moveX, moveY);

        //GridView自动滚动
        mHandler.post(mScrollRunnable);
    }

    /**
     * 交换item,并且控制item之间的显示与隐藏效果
     *
     * @param moveX
     * @param moveY
     */
    private void onSwapItem(int moveX, int moveY) {
        //获取我们手指移动到的那个item的position
        int tempPosition = pointToPosition(moveX, moveY);

        //假如tempPosition 改变了并且tempPosition不等于-1,则进行交换
        if (tempPosition != mDragPosition && tempPosition != AdapterView.INVALID_POSITION) {
            getChildAt(tempPosition - getFirstVisiblePosition()).setVisibility(View.INVISIBLE);//拖动到了新的item,新的item隐藏掉
            getChildAt(mDragPosition - getFirstVisiblePosition()).setVisibility(View.VISIBLE);//之前的item显示出来

            if (onChanageListener != null) {
                onChanageListener.onChange(mDragPosition, tempPosition);
            }

            mDragPosition = tempPosition;
        }
    }

    /**
     * 停止拖拽我们将之前隐藏的item显示出来，并将镜像移除
     */
    private void onStopDrag() {
        getChildAt(mDragPosition - getFirstVisiblePosition()).setVisibility(View.VISIBLE);
        removeDragImage();
    }


    //------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------

    private void initHeaderGridView() {
        super.setClipChildren(false);
    }

    /**
     * Add a fixed view to appear at the top of the grid. If addHeaderView is
     * called more than once, the views will appear in the order they were
     * added. Views added using this call can take focus if they want.
     * <p/>
     * NOTE: Call this before calling setAdapter. This is so HeaderGridView can wrap
     * the supplied cursor with one that will also account for header views.
     *
     * @param v            The view to add.
     * @param data         Data to associate with this view
     * @param isSelectable whether the item is selectable
     */
    public void addHeaderView(View v, Object data, boolean isSelectable) {
        ListAdapter adapter = getAdapter();

        if (adapter != null && !(adapter instanceof HeaderViewGridAdapter)) {
            throw new IllegalStateException(
                    "Cannot add header view to grid -- setAdapter has already been called.");
        }

        FixedViewInfo info = new FixedViewInfo();
        FrameLayout fl = new FullWidthFixedViewLayout(getContext());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        fl.setLayoutParams(params);
        fl.addView(v);
        info.view = v;
        info.viewContainer = fl;
        info.data = data;
        info.isSelectable = isSelectable;
        mHeaderViewInfos.add(info);

        // in the case of re-adding a header view, or adding one later on,
        // we need to notify the observer
        if (adapter != null) {
            ((HeaderViewGridAdapter) adapter).notifyDataSetChanged();
        }
    }

    /**
     * Add a fixed view to appear at the top of the grid. If addHeaderView is
     * called more than once, the views will appear in the order they were
     * added. Views added using this call can take focus if they want.
     * <p/>
     * NOTE: Call this before calling setAdapter. This is so HeaderGridView can wrap
     * the supplied cursor with one that will also account for header views.
     *
     * @param v The view to add.
     */
    public void addHeaderView(View v) {
        addHeaderView(v, null, true);
    }

    public int getHeaderViewCount() {
        return mHeaderViewInfos.size();
    }

    /**
     * Removes a previously-added header view.
     *
     * @param v The view to remove
     * @return true if the view was removed, false if the view was not a header
     * view
     */
    public boolean removeHeaderView(View v) {
        if (mHeaderViewInfos.size() > 0) {
            boolean result = false;
            ListAdapter adapter = getAdapter();
            if (adapter != null && ((HeaderViewGridAdapter) adapter).removeHeader(v)) {
                result = true;
            }
            removeFixedViewInfo(v, mHeaderViewInfos);
            return result;
        }
        return false;
    }

    private void removeFixedViewInfo(View v, ArrayList<FixedViewInfo> where) {
        int len = where.size();
        for (int i = 0; i < len; ++i) {
            FixedViewInfo info = where.get(i);
            if (info.view == v) {
                where.remove(i);
                break;
            }
        }
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (mHeaderViewInfos.size() > 0) {
            HeaderViewGridAdapter hadapter = new HeaderViewGridAdapter(mHeaderViewInfos, adapter);
            int numColumns = getNumColumns();
            if (numColumns > 1) {
                hadapter.setNumColumns(numColumns);
            }
            super.setAdapter(hadapter);
        } else {
            super.setAdapter(adapter);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ListAdapter adapter = getAdapter();
        if (adapter != null && adapter instanceof HeaderViewGridAdapter) {
            ((HeaderViewGridAdapter) adapter).setNumColumns(getNumColumns());
        }
    }

    @Override
    public void setClipChildren(boolean clipChildren) {
        // Ignore, since the header rows depend on not being clipped
    }

    /**
     * @author liujianguang
     */
    public interface OnChanageListener {

        /**
         * 当item交换位置的时候回调的方法，我们只需要在该方法中实现数据的交换即可
         *
         * @param form 开始的position
         * @param to   拖拽到的position
         */
        void onChange(int form, int to);
    }

    /**
     * A class that represents a fixed view in a list, for example a header at the top
     * or a footer at the bottom.
     */
    private static class FixedViewInfo {
        /**
         * The view to add to the grid
         */
        public View view;
        public ViewGroup viewContainer;
        /**
         * The data backing the view. This is returned from {@link ListAdapter#getItem(int)}.
         */
        public Object data;
        /**
         * <code>true</code> if the fixed view should be selectable in the grid
         */
        public boolean isSelectable;
    }

    /**
     * ListAdapter used when a HeaderGridView has header views. This ListAdapter
     * wraps another one and also keeps track of the header views and their
     * associated data objects.
     * <p>This is intended as a base class; you will probably not need to
     * use this class directly in your own code.
     */
    private static class HeaderViewGridAdapter implements WrapperListAdapter, Filterable {

        // This is used to notify the container of updates relating to number of columns
        // or headers changing, which changes the number of placeholders needed
        private final DataSetObservable mDataSetObservable = new DataSetObservable();

        private final ListAdapter mAdapter;
        private final boolean mIsFilterable;
        // This ArrayList is assumed to NOT be null.
        ArrayList<FixedViewInfo> mHeaderViewInfos;

        boolean mAreAllFixedViewsSelectable;
        private int mNumColumns = 1;

        public HeaderViewGridAdapter(ArrayList<FixedViewInfo> headerViewInfos, ListAdapter adapter) {
            mAdapter = adapter;
            mIsFilterable = adapter instanceof Filterable;

            if (headerViewInfos == null) {
                throw new IllegalArgumentException("headerViewInfos cannot be null");
            }
            mHeaderViewInfos = headerViewInfos;

            mAreAllFixedViewsSelectable = areAllListInfosSelectable(mHeaderViewInfos);
        }

        public int getHeadersCount() {
            return mHeaderViewInfos.size();
        }

        @Override
        public boolean isEmpty() {
            return (mAdapter == null || mAdapter.isEmpty()) && getHeadersCount() == 0;
        }

        public void setNumColumns(int numColumns) {
            if (numColumns < 1) {
                throw new IllegalArgumentException("Number of columns must be 1 or more");
            }
            if (mNumColumns != numColumns) {
                mNumColumns = numColumns;
                notifyDataSetChanged();
            }
        }

        private boolean areAllListInfosSelectable(ArrayList<FixedViewInfo> infos) {
            if (infos != null) {
                for (FixedViewInfo info : infos) {
                    if (!info.isSelectable) {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean removeHeader(View v) {
            for (int i = 0; i < mHeaderViewInfos.size(); i++) {
                FixedViewInfo info = mHeaderViewInfos.get(i);
                if (info.view == v) {
                    mHeaderViewInfos.remove(i);

                    mAreAllFixedViewsSelectable = areAllListInfosSelectable(mHeaderViewInfos);

                    mDataSetObservable.notifyChanged();
                    return true;
                }
            }

            return false;
        }

        @Override
        public int getCount() {
            if (mAdapter != null) {
                return getHeadersCount() * mNumColumns + mAdapter.getCount();
            } else {
                return getHeadersCount() * mNumColumns;
            }
        }

        @Override
        public boolean areAllItemsEnabled() {
            if (mAdapter != null) {
                return mAreAllFixedViewsSelectable && mAdapter.areAllItemsEnabled();
            } else {
                return true;
            }
        }

        @Override
        public boolean isEnabled(int position) {
            // Header (negative positions will throw an ArrayIndexOutOfBoundsException)
            int numHeadersAndPlaceholders = getHeadersCount() * mNumColumns;
            if (position < numHeadersAndPlaceholders) {
                return (position % mNumColumns == 0)
                        && mHeaderViewInfos.get(position / mNumColumns).isSelectable;
            }

            // Adapter
            final int adjPosition = position - numHeadersAndPlaceholders;
            int adapterCount = 0;
            if (mAdapter != null) {
                adapterCount = mAdapter.getCount();
                if (adjPosition < adapterCount) {
                    return mAdapter.isEnabled(adjPosition);
                }
            }

            throw new ArrayIndexOutOfBoundsException(position);
        }

        @Override
        public Object getItem(int position) {
            // Header (negative positions will throw an ArrayIndexOutOfBoundsException)
            int numHeadersAndPlaceholders = getHeadersCount() * mNumColumns;
            if (position < numHeadersAndPlaceholders) {
                if (position % mNumColumns == 0) {
                    return mHeaderViewInfos.get(position / mNumColumns).data;
                }
                return null;
            }

            // Adapter
            final int adjPosition = position - numHeadersAndPlaceholders;
            int adapterCount = 0;
            if (mAdapter != null) {
                adapterCount = mAdapter.getCount();
                if (adjPosition < adapterCount) {
                    return mAdapter.getItem(adjPosition);
                }
            }

            throw new ArrayIndexOutOfBoundsException(position);
        }

        @Override
        public long getItemId(int position) {
            int numHeadersAndPlaceholders = getHeadersCount() * mNumColumns;
            if (mAdapter != null && position >= numHeadersAndPlaceholders) {
                int adjPosition = position - numHeadersAndPlaceholders;
                int adapterCount = mAdapter.getCount();
                if (adjPosition < adapterCount) {
                    return mAdapter.getItemId(adjPosition);
                }
            }
            return -1;
        }

        @Override
        public boolean hasStableIds() {
            if (mAdapter != null) {
                return mAdapter.hasStableIds();
            }
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Header (negative positions will throw an ArrayIndexOutOfBoundsException)
            int numHeadersAndPlaceholders = getHeadersCount() * mNumColumns;
            if (position < numHeadersAndPlaceholders) {
                View headerViewContainer = mHeaderViewInfos
                        .get(position / mNumColumns).viewContainer;
                if (position % mNumColumns == 0) {
                    return headerViewContainer;
                } else {
                    if (convertView == null) {
                        convertView = new View(parent.getContext());
                    }
                    // We need to do this because GridView uses the height of the last item
                    // in a row to determine the height for the entire row.
                    convertView.setVisibility(View.INVISIBLE);
                    convertView.setMinimumHeight(headerViewContainer.getHeight());
                    return convertView;
                }
            }

            // Adapter
            final int adjPosition = position - numHeadersAndPlaceholders;
            int adapterCount = 0;
            if (mAdapter != null) {
                adapterCount = mAdapter.getCount();
                if (adjPosition < adapterCount) {
                    return mAdapter.getView(adjPosition, convertView, parent);
                }
            }

            throw new ArrayIndexOutOfBoundsException(position);
        }

        @Override
        public int getItemViewType(int position) {
            int numHeadersAndPlaceholders = getHeadersCount() * mNumColumns;
            if (position < numHeadersAndPlaceholders && (position % mNumColumns != 0)) {
                // Placeholders get the last view type number
                return mAdapter != null ? mAdapter.getViewTypeCount() : 1;
            }
            if (mAdapter != null && position >= numHeadersAndPlaceholders) {
                int adjPosition = position - numHeadersAndPlaceholders;
                int adapterCount = mAdapter.getCount();
                if (adjPosition < adapterCount) {
                    return mAdapter.getItemViewType(adjPosition);
                }
            }

            return AdapterView.ITEM_VIEW_TYPE_HEADER_OR_FOOTER;
        }

        @Override
        public int getViewTypeCount() {
            if (mAdapter != null) {
                return mAdapter.getViewTypeCount() + 1;
            }
            return 2;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            mDataSetObservable.registerObserver(observer);
            if (mAdapter != null) {
                mAdapter.registerDataSetObserver(observer);
            }
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            mDataSetObservable.unregisterObserver(observer);
            if (mAdapter != null) {
                mAdapter.unregisterDataSetObserver(observer);
            }
        }

        @Override
        public Filter getFilter() {
            if (mIsFilterable) {
                return ((Filterable) mAdapter).getFilter();
            }
            return null;
        }

        @Override
        public ListAdapter getWrappedAdapter() {
            return mAdapter;
        }

        public void notifyDataSetChanged() {
            mDataSetObservable.notifyChanged();
        }
    }

    private class FullWidthFixedViewLayout extends FrameLayout {
        public FullWidthFixedViewLayout(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int targetWidth = DragGridView.this.getMeasuredWidth()
                    - DragGridView.this.getPaddingLeft()
                    - DragGridView.this.getPaddingRight();
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(targetWidth,
                    MeasureSpec.getMode(widthMeasureSpec));
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


}

