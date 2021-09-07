package cn.misection.util.jsutil;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName JavaScriptUtil
 * @Description TODO
 * @CreateTime 2021年09月06日 22:20:00
 */
public class JavaScriptUtil {

    private JavaScriptUtil() {
        throw new RuntimeException(String.format("here are no %s instance for you", getClass().getName()));
    }

    public static String decoratedWithJsUrl(String jsScript) {
        return String.format("javascript:(function(){%s})()", jsScript);
    }
}
