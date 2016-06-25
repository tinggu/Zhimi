package com.linjin.zhimi.base.viewholder;

import android.view.ViewGroup;
 

/**
 * 因为 CommonListAdapter 里边无法对于未知类型的 Class 进行实例化
 * 所以需要如果想用 CommonListAdapter，必须要在对应的 CommonViewHolder 实例化一个 HOLDER_CREATOR
 * 注意：public static ViewHolderCreator HOLDER_CREATOR，名字与修饰符都不能更改，否则有可能引发失败
 * 具体样例可参见 DiscoverItemHolder
 * 如果不使用 CommonListAdapter，则不需要实例化 ViewHolderCreator
 *
 * @param <VH>
 */
public interface ViewHolderCreator<VH extends CommonViewHolder> {
    VH createView(ViewGroup parent, int viewType);
}