package com.swt.shoppingmall.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Fragment 基类
 * Created by swt on 2016/11/17.
 */
public abstract class BaseFragment extends Fragment {
    public Activity mActivity;

    /**
     * fragment 创建
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();//在onAttach()之前就已经创建好了Activity,所以不可能为空,但是业务逻辑不一定执行完了
    }

    /**
     * 初始化fragment的布局文件
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 依附的Activity创建完成
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化控件,必须由子类实现，因为不同的子页面，初始化的控件是不同的
     */
    public abstract View initView();

    /**
     * 初始化数据,不一定要让子类初始化,因为子类可能只是一个页面，没有数据
     */
    public void initData() {

    }

}
