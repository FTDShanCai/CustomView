package com.example.customview.google.fragment;

import androidx.fragment.app.Fragment;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2021/1/9 0009
 * description:
 * ******************************
 */
public class BaseLazyFragment extends Fragment {

    private boolean isLazyLoadFinish = false;

    //其他方法省略.....

    @Override
    public void onResume() {
        super.onResume();
        if (!isLazyLoadFinish) {
            lazyLoad();
            isLazyLoadFinish = true;
        }
    }

    /**
     * 页面懒加载操作 根据需求重写实现此方法
     */
    protected void lazyLoad() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
