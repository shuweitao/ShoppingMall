package com.swt.shoppingmall.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.swt.shoppingmall.R;
import com.swt.shoppingmall.base.BaseFragment;
import com.swt.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.swt.shoppingmall.home.bean.ResultBeanData;
import com.swt.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * 首页
 * Created by swt on 2016/12/23.
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    @Bind(R.id.tv_search_home)
    EditText tvSearchHome;
    @Bind(R.id.tv_message_home)
    TextView tvMessageHome;
    @Bind(R.id.ib_top)
    ImageButton ibTop;
    @Bind(R.id.rl_home)
    RecyclerView rlHome;
    private HomeFragmentAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.home_fragment, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    /**
     * 从服务器获取数据
     */
    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            Log.e(TAG, e.getMessage());
                        } catch (Exception e2) {
                            Log.e(TAG, e2.getMessage());
                        }
                    }

                    /**
                     * 当联网请求成功的时候回调
                     * @param response 成功后返回的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, response);
                        //解析Json
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        ResultBeanData.ResultBean resultBean = resultBeanData.getResult();
        if (resultBean != null) {
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(mActivity, resultBean);
            rlHome.setAdapter(adapter);
            rlHome.setLayoutManager(new GridLayoutManager(mActivity, 1));
        } else {
            //没有数据

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_message_home, R.id.ib_top})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_message_home:
                Toast.makeText(mActivity, "待开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_top:

                //回到顶部
                rlHome.scrollToPosition(0);
                break;
        }
    }
}
