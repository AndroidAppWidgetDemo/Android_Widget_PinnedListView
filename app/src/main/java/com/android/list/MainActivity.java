package com.android.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.list.data.CityBean;
import com.android.list.widget.PinnedSectionListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 数据
     */
    private ArrayList<CityBean> mDataList;

    /**
     * UI
     */
    private PinnedSectionListView pinned_section_list;
    private TestListAdapter indexAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 数据
         */
        mDataList = initData();

        //实例化拥有悬停头的控件
        pinned_section_list = (PinnedSectionListView) findViewById(R.id.pinned_section_list);
        //
        indexAdapter = new TestListAdapter(this, mDataList);
        pinned_section_list.setAdapter(indexAdapter);
    }

    /**
     * 初始化数据
     *
     * @return
     */
    private ArrayList<CityBean> initData() {
        ArrayList<CityBean> mDataList = new ArrayList<CityBean>();

        for (int i = 0; i < 100; i++) {
            CityBean bean = new CityBean();
            bean.cityId = i;
            bean.cityName = String.valueOf(i);
            //
            mDataList.add(bean);
            if (i < 10) {
                bean.groupId = 1;
                continue;
            }
            if (i < 20) {
                bean.groupId = 2;
                continue;
            }
            if (i < 30) {
                bean.groupId = 3;
                continue;
            }
            if (i < 40) {
                bean.groupId = 4;
                continue;
            }
            if (i < 50) {
                bean.groupId = 5;
                continue;
            }
            if (i < 60) {
                bean.groupId = 6;
                continue;
            }
            if (i < 70) {
                bean.groupId = 7;
                continue;
            }
            if (i < 80) {
                bean.groupId = 8;
                continue;
            }
            if (i < 90) {
                bean.groupId = 9;
                continue;
            }
            if (i < 100) {
                bean.groupId = 10;
                continue;
            }

        }

        return mDataList;
    }

}
