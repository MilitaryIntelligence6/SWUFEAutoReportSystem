package cn.misection.autoreport.common.constant;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumCampus
 * @Description TODO
 * @CreateTime 2021年09月06日 20:46:00
 */
public enum EnumCampus {

    /**
     * 柳林校区;
     */
    LIU_LIN("柳林校区"),

    /**
     * 光华校区;
     */
    GUANG_HUA("光华校区"),

    /**
     * 用户自定义;
     */
    CUSTOM(ConstString.EMPTY.value()),
    ;

    private final String chineseValue;

    EnumCampus(String chineseValue) {
        this.chineseValue = chineseValue;
    }

    public String getChineseValue() {
        return chineseValue;
    }

    public static EnumCampus selectByOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal > values().length) {
            throw new IllegalArgumentException("select ordinal only can be in the range of (0, values().length)");
        }
        return values()[ordinal];
    }
}
