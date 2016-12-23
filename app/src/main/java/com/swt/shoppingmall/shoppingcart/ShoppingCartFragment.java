package com.swt.shoppingmall.shoppingcart;

import android.view.View;

import com.swt.shoppingmall.R;
import com.swt.shoppingmall.base.BaseFragment;

/**
 * Created by swt on 2016/12/23.
 */

public class ShoppingCartFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.shopping_fragment, null);
        return view;
    }
}
