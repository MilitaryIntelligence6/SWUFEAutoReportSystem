package cn.misection.util.oututil.outstream;

import android.content.Context;
import android.view.View;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName OutStream
 * @Description TODO
 * @CreateTime 2021年04月20日 17:19:00
 */
public interface IOutStream {

    /**
     * println;
     * @param context
     * @param msg
     */
    void printt(Context context, String msg);

    /**
     * printf;
     * @param context
     * @param fmt
     * @param args
     */
    void printfToast(Context context, String fmt, Object... args);

    /**
     * snackbar show;
     * @param view
     * @param msg
     */
    void prints(View view, String msg);

    /**
     * printf;
     * @param view
     * @param fmt
     * @param args
     */
    void printfSnackbar(View view, String fmt, Object... args);


    /**
     * echo;
     * @param msg
     */
    void echo(String msg);

    /**
     * echo;
     * @param msg
     */
    void log(String msg);
}
