package com.example.customview.google.fragment;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import java.util.List;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2021/1/9 0009
 * description:xxx
 * ******************************
 */
public class ActivityUtils {

    /**
     * 显示Fragment
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void addOrShowFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                   @NonNull Fragment fragment, int frameId) {
        addOrShowFragmentToActivity(fragmentManager, fragment, frameId, "");
    }

    public static void addOrShowFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                   @NonNull Fragment fragment, int frameId, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!TextUtils.isEmpty(tag)) {//存在TAG的
            Fragment tagFragment = fragmentManager.findFragmentByTag(tag);
            List<Fragment> fragments = fragmentManager.getFragments();
            if (tagFragment != null) {
                transaction.show(tagFragment);
            } else {
                transaction.add(frameId, fragment, tag);
            }
            for (Fragment f : fragments) {
                if (!tag.equals(f.getTag())) {
                    transaction.hide(f);
                    transaction.setMaxLifecycle(f, Lifecycle.State.STARTED);
                } else {
                    transaction.setMaxLifecycle(f, Lifecycle.State.RESUMED);
                }
            }
            transaction.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        } else {
            List<Fragment> fragments = fragmentManager.getFragments();
            if (fragments != null) {
                for (Fragment f : fragments) {
                    if (f.toString().equals(fragment.toString())) {
                        transaction.show(fragment);
                        transaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
                    } else {
                        transaction.hide(f);
                        transaction.setMaxLifecycle(fragment, Lifecycle.State.STARTED);
                    }

                }
                if (fragments.contains(fragment)) {
                    transaction.commitAllowingStateLoss();
                    return;
                }
            }
            transaction.add(frameId, fragment);
            transaction.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void clearAttachFragmentInActivity(@NonNull FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (!fragments.isEmpty()) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            for (Fragment fragment : fragments) {
                transaction.remove(fragment);
            }
            transaction.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }
}

