package cn.misection.util.oututil.proxy;

import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName SnackbarUtil
 * @Description TODO
 * @CreateTime 2021年04月24日 21:53:00
 */
public final class SnackbarUtil {

    private SnackbarUtil() {

    }

    public static void show(View view, String msg) {
        Snackbar.make(
                view,
                msg,
                BaseTransientBottomBar.LENGTH_SHORT
        ).show();
    }
}
