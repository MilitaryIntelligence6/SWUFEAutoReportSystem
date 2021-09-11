//package cn.misection.autoreport.common.util.timeutil;
//
//import java.util.Calendar;
//import java.util.TimeZone;
//
///**
// * @author Military Intelligence 6 root
// * @version 1.0.0
// * @ClassName TimeUtil
// * @Description TODO
// * @CreateTime 2021年09月09日 11:16:00
// */
//public class TimeUtil {
//
//    private static final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
//
//    private TimeUtil() {
//
//    }
//
//    public static HourMinuteUnit timeForeMinutesUnit(int foreMinutes) {
//        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE) - foreMinutes;
//        if (minute < 0) {
//            minute += 60;
//            --hourOfDay;
//            if (hourOfDay < 0) {
//                hourOfDay += 24;
//            }
//        }
//        return new HourMinuteUnit(hourOfDay, minute);
//    }
//}
