package com.swt.shoppingmall.user;

import android.view.View;

import com.swt.shoppingmall.R;
import com.swt.shoppingmall.base.BaseFragment;

/**
 * Created by swt on 2016/12/23.
 */

public class UserFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.user_fragment, null);
        return view;
    }
}
