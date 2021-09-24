package cn.misection.autoreport.common.constant;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumDestnations
 * @Description TODO
 * @CreateTime 2021年09月24日 23:51:00
 */
public enum EnumDestination {

    /**
     * 东门;
     */
    EAST_GATE_WAY("东门"),

    WEST_GATE_WAY("西门"),

    CANYON("召唤师峡谷"),

    CUSTOM(ConstString.EMPTY.value()),
    ;

    private final String chineseValue;

    EnumDestination(String chineseValue) {
        this.chineseValue = chineseValue;
    }

    public String getChineseValue() {
        return chineseValue;
    }
}
