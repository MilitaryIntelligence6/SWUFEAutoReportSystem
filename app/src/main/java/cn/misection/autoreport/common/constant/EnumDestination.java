package cn.misection.autoreport.common.constant;

import androidx.annotation.IdRes;

import cn.misection.autoreport.R;

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
    EAST_GATE_WAY(R.id.default_destination_east_radio_button, "东门"),

    WEST_GATE_WAY(R.id.default_destination_west_radio_button, "西门"),

    CANYON(R.id.default_destination_canyon_radio_button, "召唤师峡谷"),

    CUSTOM(R.id.destination_show_custom_radio_button, ConstString.EMPTY.value()),
    ;

    private final @IdRes int resId;

    private final String chineseValue;

    EnumDestination(@IdRes int resId, String chineseValue) {
        this.resId = resId;
        this.chineseValue = chineseValue;
    }

    public String getChineseValue() {
        return chineseValue;
    }

    public int getResId() {
        return resId;
    }
}
