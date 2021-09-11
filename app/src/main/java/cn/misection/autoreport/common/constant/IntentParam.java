package cn.misection.autoreport.common.constant;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName IntentKey
 * @Description TODO
 * @CreateTime 2021年09月09日 10:42:00
 */
public enum IntentParam {

    /**
     * intent 传参 key;
     */
    REPORT_INFO("reportInfo"),
    ;

    private final String key;

    IntentParam(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
