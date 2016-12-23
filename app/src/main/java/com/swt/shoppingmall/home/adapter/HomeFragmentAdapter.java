package com.swt.shoppingmall.home.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.swt.shoppingmall.R;
import com.swt.shoppingmall.home.bean.ResultBeanData;
import com.swt.shoppingmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页HomeFragment页面设置数据
 * Created by swt on 2016/12/23.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    /**
     * 广告条幅类型 Banner
     */
    public static final int BANNER = 0;

    /**
     * 频道类型 GridView
     */
    public static final int CHANNEL = 1;

    /**
     * 活动类型 ViewPage
     */
    public static final int ACTION = 2;

    /**
     * 秒杀类型 ViewPage
     */
    public static final int SECKILL = 3;

    /**
     * 推荐类型 ViewPage
     */
    public static final int RECOMMEND = 4;

    /**
     * 热卖类型 ViewPage
     */
    public static final int HOT = 5;

    /**
     * 当前类型
     */
    private int currentType = 0;

    private Activity mActivity;
    private ResultBeanData.ResultBean resultBean;
    private final LayoutInflater mLayoutInflater;

    public HomeFragmentAdapter(Activity mActivity, ResultBeanData.ResultBean resultBean) {
        this.mActivity = mActivity;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mActivity);//用于初始化布局文件
    }

    /**
     * 创建ViewHolder 相当于ListView中的getView()方法
     *
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (BANNER == viewType) {
            return new BannerViewHolder(mActivity, mLayoutInflater.inflate(R.layout.banner_viewpager, null), resultBean);
        }
        return null;
    }

    /**
     * 相当于geiView()中绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (BANNER == getItemViewType(position)) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }
    }

    /**
     * 得到类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACTION:
                currentType = ACTION;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;

        }
        return currentType;
    }

    /**
     * 总共有多少个item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        //开发过程中从1-->2,避免报错
        return 1;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private Activity activity;
        private Banner banner;

        public BannerViewHolder(Activity activity, View view, ResultBeanData.ResultBean resultBean) {
            super(view);
            this.activity = activity;
            this.banner = (Banner) view.findViewById(R.id.banner);
        }

        /**
         * 设置Banner的数据
         *
         * @param banner_info
         */
        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //得到图片地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imgUrl = banner_info.get(i).getImage();
                imagesUrl.add(imgUrl);
            }
            //设置循环指示器
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setBannerAnimation(Transformer.Accordion);//设置手风琴效果
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //设置图片
                    Glide.with(activity).load(Constants.BASE_URL_IMAGE + url).into(view);
                }
            });
            //设置item的点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mActivity, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
