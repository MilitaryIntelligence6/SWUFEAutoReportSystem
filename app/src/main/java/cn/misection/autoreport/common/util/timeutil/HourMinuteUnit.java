package cn.misection.autoreport.common.util.timeutil;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Administrator
 */
public class HourMinuteUnit {

    private static final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));

    private static final Map<Integer, HourMinuteUnit> prevUnitCache = new HashMap<>();

    private int hourOfDay;

    private int minute;

    public HourMinuteUnit(int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
    }

    public HourMinuteUnit() {
    }

    public static HourMinuteUnit timePrevMinutesUnit(int foreMinutes) {
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE) - foreMinutes;
        if (minute < 0) {
            minute += 60;
            --hourOfDay;
            if (hourOfDay < 0) {
                hourOfDay += 24;
            }
        }
        HourMinuteUnit unit;
        // 不能用 contains, 怕出现取出来为 null;
        if ((unit = prevUnitCache.get(foreMinutes)) == null) {
            unit = new HourMinuteUnit(hourOfDay, minute);
            prevUnitCache.put(foreMinutes, unit);
        } else {
            unit.hourOfDay = hourOfDay;
            unit.minute = minute;
        }
        return unit;
    }

    public String toFormatString() {
        return String.format("%02d:%02d", hourOfDay, minute);
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}