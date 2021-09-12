package cn.misection.autoreport.common.constant;

import androidx.annotation.IdRes;

import cn.misection.autoreport.R;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName Preference
 * @Description TODO
 * @CreateTime 2021年09月12日 00:10:00
 */
public enum ReportPreferences {

    /**
     * 配置各种属性;
     */
    CAMPUS("campus", 0, "柳林",R.id.campus_radio_group),

    DESTINATION("destination", 0, "东门", R.id.destination_radio_group),

    TRANSPORTATION("transportation", 0, "步行", R.id.transportation_radio_group),

    REASON("reason", 0, "恰饭", R.id.reason_radio_group),
    ;

    private final String key;

    private final int defaultSelect;

    private final String defaultCustomValue;

    private final @IdRes int resId;

    ReportPreferences(String key, int defaultSelect, String defaultCustomValue, int resId) {
        this.key = key;
        this.defaultSelect = defaultSelect;
        this.defaultCustomValue = defaultCustomValue;
        this.resId = resId;
    }

    public String getKey() {
        return key;
    }

    public int getDefaultSelect() {
        return defaultSelect;
    }

    public int getResId() {
        return resId;
    }
}
