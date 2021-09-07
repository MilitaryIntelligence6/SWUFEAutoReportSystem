package cn.misection.util.oututil.system;

import cn.misection.util.oututil.outstream.IOutStream;
import cn.misection.util.oututil.outstream.UiOutStream;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName AppSystem
 * @Description TODO
 * @CreateTime 2021年04月23日 12:35:00
 */
public final class AppSystem {

    public static final IOutStream out = UiOutStream.getInstance();

    private AppSystem() {
        throw new RuntimeException(String.format("here are no %s instance for you", getClass().getName()));
    }
}
