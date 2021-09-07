package cn.misection.autoreport.constant;

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
    ;

    private final String chineseLiteral;

    EnumCampus(String chineseLiteral) {
        this.chineseLiteral = chineseLiteral;
    }

    public String getChineseLiteral() {
        return chineseLiteral;
    }
}
