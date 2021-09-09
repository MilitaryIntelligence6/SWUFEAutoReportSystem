package cn.misection.autoreport.constant;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumCampus
 * @Description TODO
 * @CreateTime 2021年09月06日 20:46:00
 */
public enum Campus {

    /**
     * 柳林校区;
     */
    LIU_LIN("柳林校区"),

    /**
     * 光华校区;
     */
    GUANG_HUA("光华校区"),
    ;

    private final String chineseLiteral;

    Campus(String chineseLiteral) {
        this.chineseLiteral = chineseLiteral;
    }

    public String getChineseLiteral() {
        return chineseLiteral;
    }

    public static Campus selectByOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal > values().length) {
            throw new IllegalArgumentException("select ordinal only can be in the range of (0, values().length)");
        }
        return values()[ordinal];
    }
}
