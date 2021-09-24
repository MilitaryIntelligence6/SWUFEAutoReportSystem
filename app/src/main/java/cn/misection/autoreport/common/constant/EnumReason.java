package cn.misection.autoreport.common.constant;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumReason
 * @Description TODO
 * @CreateTime 2021年09月24日 23:54:00
 */
public enum EnumReason {

    /**
     * 理由;
     */
    EAT("恰饭"),

    FIND_JOB("找工作"),

    ENLIGHTENMENT("悟道"),

    CUSTOM(ConstString.EMPTY.value()),
    ;

    private final String chineseValue;

    EnumReason(String chineseValue) {
        this.chineseValue = chineseValue;
    }

    public String getChineseValue() {
        return chineseValue;
    }
}
