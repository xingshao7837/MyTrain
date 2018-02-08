package com.example.administrator.mytrain.uitls;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Fragment 工具类
 * Created by 90589 on 2018/2/8.
 */
public class FragmentUtil {
    /**
     * 显示fragment
     *
     * @param fragmentManager v4 fragmentManager
     * @param list            fragment集合
     * @param fragment        要显示的fragment
     * @param ViewId          布局id
     */
    public static void showFragment(FragmentManager fragmentManager, List<? extends Fragment> list, Fragment fragment, @IdRes int ViewId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(ViewId, fragment);
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isAdded())
                fragmentTransaction.hide(list.get(i));
        }
        fragmentTransaction.show(fragment).commit();
    }
}

