package com.android.list;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.android.list.data.CityBean;
import com.android.list.widget.PinnedItemData;
import com.android.list.widget.PinnedSectionListView;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter
 */
public class TestListAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter, SectionIndexer {


    private Context context;

    /**
     * 传入的分组数据
     */
    // 传入的数据列表
    private List<CityBean> mDataList;
    // Group数组
    private List<PinnedItemData> mGroupList = new ArrayList<PinnedItemData>();
    // 包含Group和item的数组
    private List<PinnedItemData> mItemList = new ArrayList<PinnedItemData>();

    /**
     * @param context
     * @param dataList 分组数据
     */
    public TestListAdapter(Context context, List<CityBean> dataList) {
        this.context = context;
        // 分组数据
        this.mDataList = dataList;
        //
        initData();


    }

    /**
     * 构造 分组数组、全部列表 数组
     */
    private void initData() {

        // #########Group数组#########
        int indexGroup = 0;
        int indexItem = 0;
        //
        for (int i = 0; i < mDataList.size(); i++) {
            CityBean bean = mDataList.get(i);
            //
            PinnedItemData itemData = null;
            if (i == 0 || (bean.groupId != mDataList.get(i - 1).groupId)) {
                itemData = new PinnedItemData(PinnedItemData.ITEM_GROUP, bean);
                itemData.indexGroup = (++indexGroup);
                itemData.indexItem = (++indexItem);
                mGroupList.add(itemData);
                //
                mItemList.add(itemData);
            }
            itemData = new PinnedItemData(PinnedItemData.ITEM_NORML, bean);
            itemData.indexGroup = indexGroup;
            itemData.indexItem = (++indexItem);
            mItemList.add(itemData);
        }
    }


    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public PinnedItemData getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).type;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v;
        ViewHolder vh;
        // 从集合中获取当前行的数据
        PinnedItemData item = mItemList.get(position);

        if (getItemViewType(position) == PinnedItemData.ITEM_NORML) {
            if (view == null) {
                // 说明当前这一行不是重用的
                // 加载行布局文件，产生具体的一行
                v = View.inflate(context, R.layout.item, null);
                // 创建存储一行控件的对象
                vh = new ViewHolder();
                v.setBackgroundResource(R.color.green_light);
                // 将该行的控件全部存储到vh中
                vh.tvName = (TextView) v.findViewById(R.id.text_text);
                v.setTag(vh);// 将vh存储到行的Tag中
            } else {
                v = view;
                // 取出隐藏在行中的Tag--取出隐藏在这一行中的vh控件缓存对象
                vh = (ViewHolder) view.getTag();
            }
            vh.tvName.setText(((CityBean) item.mData).cityName);
        } else {
            if (view == null) {
                // 说明当前这一行不是重用的
                // 加载行布局文件，产生具体的一行
                v = View.inflate(context, R.layout.item, null);
                // 创建存储一行控件的对象
                vh = new ViewHolder();
                // 将该行的控件全部存储到vh中
                vh.tvName = (TextView) v.findViewById(R.id.text_text);
                v.setTag(vh);// 将vh存储到行的Tag中
            } else {
                v = view;
                // 取出隐藏在行中的Tag--取出隐藏在这一行中的vh控件缓存对象
                vh = (ViewHolder) view.getTag();
            }
            v.setBackgroundResource(R.color.blue_light);
            vh.tvName.setText(((CityBean) item.mData).cityName);
        }
        return v;
    }


    /**
     * {@link PinnedSectionListView.PinnedSectionListAdapter} 中方法###############
     *
     * @param viewType
     * @return
     */
    //当前view是否属于固定的item
    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == PinnedItemData.ITEM_GROUP;
    }

    /**
     * {@link SectionIndexer} 中方法 ################
     *
     * @return
     */
    //  {@link SectionIndexer} 中方法
    @Override
    public PinnedItemData[] getSections() {
        return (PinnedItemData[]) mGroupList.toArray();
    }


    /**
     * {@link SectionIndexer} 中方法
     * 通过position 找到Group 的index
     * <p>
     * Given a position within the adapter, returns the index of the
     * corresponding section within the array of section objects.
     *
     * @param position
     * @return
     */
    @Override
    public int getSectionForPosition(int position) {
        if (position >= getCount()) {
            position = getCount() - 1;
        }
        return getItem(position).indexGroup;
    }

    /**
     * {@link SectionIndexer} 中方法
     * 通过index group 找到对应的 postion
     * <p>
     * Given the index of a section within the array of section objects, returns
     * the starting position of that section within the adapter.
     *
     * @param sectionIndex group组的index
     * @return
     */
    @Override
    public int getPositionForSection(int sectionIndex) {
        if (sectionIndex >= mGroupList.size()) {
            sectionIndex = mGroupList.size() - 1;
        }
        return mGroupList.get(sectionIndex).indexItem;
    }


    // 存储一行中的控件（缓存作用）---避免多次强转每行的控件
    class ViewHolder {
        TextView tvName;
    }
}
