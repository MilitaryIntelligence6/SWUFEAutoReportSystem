package cn.misection.autoreport.common.constant;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName SwufeUrl
 * @Description TODO
 * @CreateTime 2021年09月09日 10:48:00
 */
public enum SwufePage {

    /**
     * 枚举可能遇到的 url;
     */
    LOGIN("https://authserver.swufe.edu.cn/authserver/login?service=http%3a%2f%2fqxj.iswufe.info%2fQJ%2fXSBBList"),

    REPORT("https://qxj.iswufe.info/QJ/XSBBList"),
    ;

    private final String url;

    SwufePage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
