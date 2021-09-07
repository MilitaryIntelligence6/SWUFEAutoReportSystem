package cn.misection.autoreport.constant;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName JavaScriptPool
 * @Description TODO
 * @CreateTime 2021年09月07日 11:03:00
 */
public class JavaScriptPool {

    public static final String DEFAULT_TEST_JS =
            "javascript:(function(){\n" +
                    "    // 选择柳林校区;\n" +
                    "    document.getElementById(\"szxq\").selectedIndex = 0;\n" +
                    "    // 设置出发时间;\n" +
                    "    document.getElementById(\"sbDate\").value = \"17:03\";\n" +
                    "    // 设置返校时间;\n" +
                    "    document.getElementById(\"edDate\").value = \"22:55\";\n" +
                    "    // 目的地;\n" +
                    "    document.getElementById(\"qjmdd\").value = \"东门;\";\n" +
                    "    // 出行方式;\n" +
                    "    document.getElementById(\"qjxc\").value = \"步行;\";\n" +
                    "    // 理由;\n" +
                    "    document.getElementById(\"qjyy\").value = \"学好 Java C++, 走遍天下都不怕;\\n掌握计网与算法, 面完腾讯也称霸;\";\n" +
                    "})()";

    private JavaScriptPool() {
        throw new RuntimeException(String.format("here are no %s instance for you", getClass().getName()));
    }
}