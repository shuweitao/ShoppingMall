package com.swt.shoppingmall.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.swt.shoppingmall.R;
import com.swt.shoppingmall.base.BaseFragment;
import com.swt.shoppingmall.community.CommunityFragment;
import com.swt.shoppingmall.home.fragment.HomeFragment;
import com.swt.shoppingmall.shoppingcart.ShoppingCartFragment;
import com.swt.shoppingmall.type.TypeFragment;
import com.swt.shoppingmall.user.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        initListener();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:

                        position = 0;

                        break;
                    case R.id.rb_type:

                        position = 1;

                        break;
                    case R.id.rb_community:

                        position = 2;

                        break;
                    case R.id.rb_cart:

                        position = 3;

                        break;
                    case R.id.rb_user:

                        position = 4;

                        break;
                }
                //根据位置取不同的Fragment
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragment, baseFragment);
            }
        });

        rgMain.check(R.id.rb_home);
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换Fragment
     *
     * @param fromFragment 上次显示的Fragment
     * @param nextFragment 当前要显示的Fragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.frameLayout, nextFragment);
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment);
                }
                transaction.commit();
            }
        }
    }


}
