package cn.misection.autoreport.common.constant;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumTranspotation
 * @Description TODO
 * @CreateTime 2021年09月24日 23:53:00
 */
public enum EnumTransportation {

    /**
     * 交通;
     */
    WALK("步行"),

    BIKE("骑行"),

    FLY("飞行"),

    CUSTOM(ConstString.EMPTY.value()),
    ;
    private final String chineseValue;

    EnumTransportation(String chineseValue) {
        this.chineseValue = chineseValue;
    }

    public String getChineseValue() {
        return chineseValue;
    }
}
