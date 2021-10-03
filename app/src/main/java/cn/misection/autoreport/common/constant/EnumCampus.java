package cn.misection.autoreport.common.constant;

import androidx.annotation.IdRes;

import cn.misection.autoreport.R;

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
    LIU_LIN(R.id.campus_liulin_radio_button, "柳林校区"),

    /**
     * 光华校区;
     */
    GUANG_HUA(R.id.campus_guanghua_radio_button, "光华校区"),
    ;

    private final @IdRes int resId;

    private final String chineseValue;

    EnumCampus(@IdRes int resId, String chineseValue) {
        this.resId = resId;
        this.chineseValue = chineseValue;
    }

    public String getChineseValue() {
        return chineseValue;
    }

    public int getResId() {
        return resId;
    }

    public static EnumCampus selectByOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal > values().length) {
            throw new IllegalArgumentException("select ordinal only can be in the range of (0, values().length)");
        }
        return values()[ordinal];
    }
}
