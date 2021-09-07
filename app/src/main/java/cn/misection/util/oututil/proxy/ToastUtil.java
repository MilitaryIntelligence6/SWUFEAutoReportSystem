package cn.misection.util.oututil.proxy;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ToastUtil
 * @Description TODO
 * @CreateTime 2021年04月20日 23:29:00
 */
public final class ToastUtil {

    private ToastUtil() {

    }

    public static void show(Context context, String msg) {
        Toast.makeText(
                context,
                msg,
                Toast.LENGTH_SHORT
        ).show();
    }
}
